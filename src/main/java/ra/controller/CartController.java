package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Cart;
import ra.model.entity.Product;
import ra.model.entity.ProductDetail;
import ra.model.repository.CartRepository;
import ra.model.repository.ProductRepository;
import ra.model.service.CartSevice;
import ra.model.service.ProductDetailSevice;
import ra.model.service.ProductSevice;
import ra.model.service.UserService;
import ra.payload.request.CartRequest;
import ra.payload.response.CartRespose;
import ra.security.CustomUserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@PreAuthorize("hasRole('USER')")
@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/auth/cart")
public class CartController {

    @Autowired
    private CartSevice cartSevice;

    @Autowired
    private ProductDetailSevice productDetailSevice;
    @Autowired
    private UserService userService;
    @Autowired
    private CartRepository cartRepository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("getAllCart")
    public ResponseEntity<?> getAllCart(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Cart> listCart = cartSevice.getAllCart(customUserDetails.getUserId(),page,size);
        List<CartRespose> listCartRespose = new ArrayList<>();
        for (Cart cart :listCart) {
            CartRespose cartRespose = new CartRespose();
            cartRespose.setCartId(cart.getCartId());
            cartRespose.setQuantity(cart.getQuantity());
            cartRespose.setPrice(cart.getProductDetail().getPrice());
            cartRespose.setTotalPrice(cart.getTotalPrice());
            cartRespose.setSizeName(cart.getProductDetail().getSize().getSizeName());
            cartRespose.setColorName(cart.getProductDetail().getColor().getColorName());
            cartRespose.setProductName(cart.getProductDetail().getProduct().getProductName());
            listCartRespose.add(cartRespose);
        }
        Map<String,Object> displayListCart = new HashMap<>();
        displayListCart.put("TotalPage",cartSevice.getTotalList(customUserDetails.getUserId(),size));
        displayListCart.put("ListCart",listCartRespose);
        return ResponseEntity.ok(displayListCart);
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("addToCart")
    public ResponseEntity<?> addToCart(@RequestBody CartRequest cartRequest){
        Cart cart = new Cart();
        cart.setQuantity(cartRequest.getQuantity());
        cart.setProductDetail(productDetailSevice.findById(cartRequest.getProductDetailId()));
        cart.setTotalPrice(cart.getProductDetail().getPrice()*cart.getQuantity());
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cart.setUsers(userService.findById(customUserDetails.getUserId()));
        try {
            cart = cartSevice.insertCard(cart);
            return ResponseEntity.ok("Thêm sản phẩm vào giỏ hàng thành công!!!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Thêm sản phẩm vào giỏ hàng thất bại!");
        }

    }
    @PutMapping("updateCart")
    public ResponseEntity<?> updateCart(@RequestBody CartRequest cartRequest,@RequestParam("cartId") int cartId){
        Cart cart = cartSevice.findById(cartId);
        ProductDetail productDetail = productDetailSevice.findById(cartRequest.getQuantity());
        cart.setProductDetail(productDetail);
        cart.setQuantity(cartRequest.getQuantity());
        cart.setTotalPrice(productDetail.getPrice()*cart.getQuantity());
        try {
            cart = cartSevice.insertCard(cart);
            return ResponseEntity.ok("Sửa giỏ hàng thành công!!!");
        }catch (Exception e){
            return ResponseEntity.ok("Sửa giỏ hàng thất bại!!!");
        }



    }



}
