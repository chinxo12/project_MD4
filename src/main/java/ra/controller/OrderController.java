package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Cart;
import ra.model.entity.OrderDetail;
import ra.model.entity.Users;
import ra.model.repository.CartRepository;
import ra.model.service.CartSevice;
import ra.model.service.OrderDetailSevice;
import ra.model.service.ProductDetailSevice;
import ra.model.service.UserService;
import ra.payload.request.ConfirmOrderRequest;
import ra.payload.request.OrderRequest;
import ra.payload.response.OrderDetailResponse;
import ra.payload.response.OrderResponse;
import ra.payload.response.ShopOrderResponse;
import ra.security.CustomUserDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/auth/order")
public class OrderController {
    @Autowired
    private OrderDetailSevice orderDetailSevice;
    @Autowired
    private CartSevice cartSevice;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductDetailSevice productDetailSevice;

    @GetMapping("getAllOrder")
    public ResponseEntity<?> getAllOrder() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAll(customUserDetails.getUserId());
        List<OrderDetailResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            OrderDetailResponse order = new OrderDetailResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.isOrderStatus());
            order.setPrice(orderDetail.getPrice());
            order.setQuantity(orderDetail.getQuantity());
            order.setColorName(orderDetail.getProductDetail().getColor().getColorName());
            order.setSizeName(orderDetail.getProductDetail().getSize().getSizeName());
            order.setCreateDate(orderDetail.getCreateDate());
            order.setTotalPrice(order.getTotalPrice());
            order.setShopName(orderDetail.getProductDetail().getProduct().getUsers().getUserName());
            listResponse.add(order);
        }

        return ResponseEntity.ok(listResponse);

    }

    @GetMapping("getAllSuccessOrder")
    public ResponseEntity<?> getAllSuccessOrder() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllByStatus(customUserDetails.getUserId(),true);
        List<OrderDetailResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            OrderDetailResponse order = new OrderDetailResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.isOrderStatus());
            order.setPrice(orderDetail.getPrice());
            order.setQuantity(orderDetail.getQuantity());
            order.setColorName(orderDetail.getProductDetail().getColor().getColorName());
            order.setSizeName(orderDetail.getProductDetail().getSize().getSizeName());
            order.setCreateDate(orderDetail.getCreateDate());
            order.setTotalPrice(order.getTotalPrice());
            order.setShopName(orderDetail.getProductDetail().getProduct().getUsers().getUserName());
            listResponse.add(order);
        }

        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("getAllWaitingOrder")
    public ResponseEntity<?> getAllWaitingOrder() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllByStatus(customUserDetails.getUserId(),false);
        List<OrderDetailResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            OrderDetailResponse order = new OrderDetailResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.isOrderStatus());
            order.setPrice(orderDetail.getPrice());
            order.setQuantity(orderDetail.getQuantity());
            order.setColorName(orderDetail.getProductDetail().getColor().getColorName());
            order.setSizeName(orderDetail.getProductDetail().getSize().getSizeName());
            order.setCreateDate(orderDetail.getCreateDate());
            order.setTotalPrice(order.getTotalPrice());
            order.setShopName(orderDetail.getProductDetail().getProduct().getUsers().getUserName());
            listResponse.add(order);
        }

        return ResponseEntity.ok(listResponse);
    }
    @GetMapping("getOrderForShop")
    public ResponseEntity<?> getOrderForShop(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.getOrderForShop(customUserDetails.getUserId());
        List<ShopOrderResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            ShopOrderResponse order = new ShopOrderResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.isOrderStatus());
            order.setPrice(orderDetail.getPrice());
            order.setQuantity(orderDetail.getQuantity());
            order.setColorName(orderDetail.getProductDetail().getColor().getColorName());
            order.setSizeName(orderDetail.getProductDetail().getSize().getSizeName());
            order.setCreateDate(orderDetail.getCreateDate());
            order.setTotalPrice(order.getTotalPrice());
            order.setUserName(orderDetail.getUsers().getUserName());
            listResponse.add(order);
        }

        return ResponseEntity.ok(listResponse);
    }
    @GetMapping("shop/getWaitingOrder")
    public ResponseEntity<?> getWaitingOrDerForShop(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllOrderByStatsus(customUserDetails.getUserId(),false);
        List<ShopOrderResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            ShopOrderResponse order = new ShopOrderResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.isOrderStatus());
            order.setPrice(orderDetail.getPrice());
            order.setQuantity(orderDetail.getQuantity());
            order.setColorName(orderDetail.getProductDetail().getColor().getColorName());
            order.setSizeName(orderDetail.getProductDetail().getSize().getSizeName());
            order.setCreateDate(orderDetail.getCreateDate());
            order.setTotalPrice(order.getTotalPrice());
            order.setUserName(orderDetail.getUsers().getUserName());
            listResponse.add(order);
        }

        return ResponseEntity.ok(listResponse);
    }
    @GetMapping("shop/getSoldOrder")
    public ResponseEntity<?> getSoldOrder(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllOrderByStatsus(customUserDetails.getUserId(),true);
        List<ShopOrderResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            ShopOrderResponse order = new ShopOrderResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.isOrderStatus());
            order.setPrice(orderDetail.getPrice());
            order.setQuantity(orderDetail.getQuantity());
            order.setColorName(orderDetail.getProductDetail().getColor().getColorName());
            order.setSizeName(orderDetail.getProductDetail().getSize().getSizeName());
            order.setCreateDate(orderDetail.getCreateDate());
            order.setTotalPrice(order.getTotalPrice());
            order.setUserName(orderDetail.getUsers().getUserName());
            listResponse.add(order);
        }

        return ResponseEntity.ok(listResponse);
    }
    @PostMapping("addOrder")
    public ResponseEntity<?> insertOrder(@RequestBody OrderRequest orderRequest) {
        boolean checkExit = true;
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users users = userService.findById(customUserDetails.getUserId());
        try {
            for (int id :orderRequest.getListCart()) {
                OrderDetail order = new OrderDetail();
                Cart cart = cartSevice.findById(id);
                order.setProductDetail(cart.getProductDetail());
                order.setPrice(cart.getProductDetail().getPrice());
                order.setUsers(users);
                order.setQuantity(cart.getQuantity());
                order.setTotalPrice(order.getPrice()*order.getQuantity());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dateNow = new Date();
                String strNow = sdf.format(dateNow);
                order.setCreateDate(sdf.parse(strNow));
                order.setOrderStatus(false);
                order = orderDetailSevice.save(order);
            }
        } catch (Exception e) {
            checkExit = false;
            e.printStackTrace();
        }
        if (checkExit) {
            for (int id : orderRequest.getListCart()) {
                cartSevice.deleteCart(id);
            }
            return ResponseEntity.ok("Mua hàng Thành công");
        } else {
            return ResponseEntity.ok("Mua hàng thất bại!");
        }
    }
    @PutMapping("comfirmOrder")
    public ResponseEntity<?> confirmOrder(@RequestBody ConfirmOrderRequest confirm){
        boolean check = true;
        try {
            for (int id :confirm.getListOrderId()) {
                OrderDetail orderDetail = orderDetailSevice.findById(id);
                orderDetail.setOrderStatus(true);
                orderDetail = orderDetailSevice.save(orderDetail);
            }

        }catch (Exception e){
            check = false;
            e.printStackTrace();
        }
        if (check){
            for (int id :confirm.getListOrderId()) {
                OrderDetail orderDetail = orderDetailSevice.findById(id);
              orderDetail.getProductDetail().setQuantity(orderDetail.getProductDetail().getQuantity()-orderDetail.getQuantity());
              orderDetail.getProductDetail().setSoldQuantity(orderDetail.getProductDetail().getSoldQuantity()+orderDetail.getQuantity());
              productDetailSevice.saveOrUpdate(orderDetail.getProductDetail());
            }
            return ResponseEntity.ok("Đã xác nhận đơn hàng thành công!");
        }else {
            return ResponseEntity.ok("Xác nhận đơn hàng thất bại!");
        }
    }
}
