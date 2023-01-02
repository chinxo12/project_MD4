package ra.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ra.jwt.JwtTokenProvider;
import ra.model.entity.*;
import ra.model.service.AddressSevice;
import ra.model.service.ProductSevice;
import ra.model.service.RoleService;
import ra.model.service.UserService;
import ra.payload.request.*;
import ra.payload.response.JwtResponse;
import ra.payload.response.MessageResponse;
import ra.security.CustomUserDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ProductSevice<Product,Integer> productSevice;
    @Autowired
    private AddressSevice addressSevice;
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if (userService.existsByUserName(signupRequest.getUserName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Usermame is already"));
        }
        if (userService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already"));
        }
        Users user = new Users();
        user.setUserName(signupRequest.getUserName());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setEmail(signupRequest.getEmail());
        user.setPhone(signupRequest.getPhone());
        user.setUserStatus(true);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNow = new Date();
        String strNow = sdf.format(dateNow);
        try {
            user.setCreated(sdf.parse(strNow));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        Set<String> strRoles = signupRequest.getListRoles();
        Set<Roles> listRoles = new HashSet<>();
        if (strRoles==null){
            //User quyen mac dinh
            Roles userRole = roleService.findByRoleName(ERole.ROLE_USER).orElseThrow(()->new RuntimeException("Error: Role is not found"));
            listRoles.add(userRole);
        }else {
            strRoles.forEach(role->{
                switch (role){
                    case "admin":
                        Roles adminRole = roleService.findByRoleName(ERole.ROLE_ADMIN)
                                .orElseThrow(()->new RuntimeException("Error: Role is not found"));
                        listRoles.add(adminRole);
                    case "moderator":
                        Roles modRole = roleService.findByRoleName(ERole.ROLE_MODERATOR)
                                .orElseThrow(()->new RuntimeException("Error: Role is not found"));
                        listRoles.add(modRole);
                    case "user":
                        Roles userRole = roleService.findByRoleName(ERole.ROLE_USER)
                                .orElseThrow(()->new RuntimeException("Error: Role is not found"));
                        listRoles.add(userRole);
                }
            });
        }
        user.setListRoles(listRoles);
        userService.saveOrUpdate(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails customUserDetail = (CustomUserDetails) authentication.getPrincipal();
            //Sinh JWT tra ve client
            String jwt = tokenProvider.generateToken(customUserDetail);
            //Lay cac quyen cua user
            List<String> listRoles = customUserDetail.getAuthorities().stream()
                    .map(item->item.getAuthority()).collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt,customUserDetail.getUserId(),customUserDetail.getUsername(),customUserDetail.getEmail(),
                    customUserDetail.getPhone(),listRoles));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Sai tên đăng nhập hoặc mật khẩu!");
        }

    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("changePassword")
    public ResponseEntity<?> changePassWord(@RequestBody ChangePasswordRequest changePasswordRequest){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users users = userService.findById(customUserDetails.getUserId());
        boolean check = encoder.matches(changePasswordRequest.getOldPassword(),users.getPassword());
        if (check){
            users.setPassword(encoder.encode(changePasswordRequest.getNewPassword()));
            try {
                userService.saveOrUpdate(users);
            }catch (Exception e){
                e.printStackTrace();
                check = false;
            }
        }else {
            return ResponseEntity.ok("Mật khẩu cũ không chính xác!");
        }
        if (check){
            return ResponseEntity.ok("Đổi mật khẩu thành công!");
        }else {
            return ResponseEntity.ok("Có lỗi trong quá trình xử lỹ vui lòng thử lại!!");
        }

    }
    @PutMapping("addWishList/{productId}")
    public ResponseEntity<?> addToWishList(@PathVariable("productId")int productId){
        Product product = productSevice.findById(productId);
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = userService.findById(customUserDetails.getUserId());
        user.getWishList().add(product);
        try {
            user = userService.saveOrUpdate(user);
            return ResponseEntity.ok("Đã thêm sản phẩm vào danh mục ưa thích");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Có lỗi trong quá trình xử lý vui lòng thử lại!");
        }
    }

    @PutMapping("removeWishList/{productId}")
    public ResponseEntity<?> removeWishList(@PathVariable("productId")int productId){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = userService.findById(customUserDetails.getUserId());
        for (Product product :user.getWishList()) {
            if (product.getProductId()==productId){
                user.getWishList().remove(productSevice.findById(productId));
                break;
            }
        }
        try {
            user = userService.saveOrUpdate(user);
            return ResponseEntity.ok("Đã bỏ yêu thích sản phẩm này!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Có lỗi trong quá trình xử lý vui lòng thử lại!");
        }
    }
    @PutMapping("update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = userService.findById(customUserDetails.getUserId());
        user.setPhone(userUpdateRequest.getPhoneNumber());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            user.setDateOfBirth(sdf.parse(userUpdateRequest.getBirthDate()));
            userService.saveOrUpdate(user);
            return ResponseEntity.ok("Đã cập nhật thông tin thành công!");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.ok("Cập nhật thông tin thất bại!");
        }
    }
    @PostMapping("addAddress")
    public ResponseEntity<?> addAddress(@RequestBody AddressRequest addressRequest){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = userService.findById(customUserDetails.getUserId());
        Address address = new Address();
        address.setAddress(addressRequest.getAddress());
        address.setFullName(addressRequest.getFullName());
        address.setPhoneNumber(addressRequest.getPhoneNumber());
        address.setUsers(user);
        try {
            addressSevice.saveOrUpdate(address);
            return ResponseEntity.ok("Đã thêm địa chỉ nhận hàng thành công!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Thêm địa chỉ nhận hàng thất bại!");
        }
    }
    @PutMapping("updateAddress/{addressId}")
    public ResponseEntity<?> updateAddress(@RequestBody AddressRequest addressRequest,@PathVariable("addressId") int addressId){

        Address address = addressSevice.findById(addressId);
        address.setAddress(addressRequest.getAddress());
        address.setFullName(addressRequest.getFullName());
        address.setPhoneNumber(address.getPhoneNumber());
        try {
            addressSevice.saveOrUpdate(address);
            return ResponseEntity.ok("Đã cập nhật địa chỉ nhận hàng thành công!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Cập nhật địa chỉ nhận hàng thất bại!");
        }
    }

    @PutMapping("delete/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") int addressId){

        try {
            addressSevice.delete(addressId);
            return ResponseEntity.ok("Đã xóa địa chỉ nhận hàng thành công!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Xóa địa chỉ nhận hàng thất bại!");
        }
    }



}
