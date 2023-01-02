package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.*;
import ra.model.repository.CartRepository;
import ra.model.service.*;
import ra.payload.request.ConfirmOrderRequest;
import ra.payload.request.OrderRequest;
import ra.payload.response.BillResponse;
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
    @Autowired
    private AddressSevice addressSevice;
    @Autowired
    private BillSevice billSevice;

    @GetMapping("user/getAllOrder")
    public ResponseEntity<?> getAllOrder() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAll(customUserDetails.getUserId());
        List<OrderDetailResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            OrderDetailResponse order = new OrderDetailResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.getOrderStatus());
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

    @GetMapping("user/getSuccessOrder")
    public ResponseEntity<?> getAllSuccessOrder() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllByStatus(customUserDetails.getUserId(),2);
        List<OrderDetailResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            OrderDetailResponse order = new OrderDetailResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.getOrderStatus());
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

    @GetMapping("user/getAllWaitingOrder")
    public ResponseEntity<?> getAllWaitingOrder() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllByStatus(customUserDetails.getUserId(),0);
        List<OrderDetailResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            OrderDetailResponse order = new OrderDetailResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.getOrderStatus());
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
    @GetMapping("user/getUserCancelOrder")
    public ResponseEntity<?> getCancelOrder() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllByStatus(customUserDetails.getUserId(),4);
        List<OrderDetailResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            OrderDetailResponse order = new OrderDetailResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.getOrderStatus());
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
    @GetMapping("user/getShopCancelOrder")
    public ResponseEntity<?> getShopCancelOrder() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllByStatus(customUserDetails.getUserId(),3);
        List<OrderDetailResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            OrderDetailResponse order = new OrderDetailResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.getOrderStatus());
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
    @GetMapping("shop/getALlorder")
    public ResponseEntity<?> getOrderForShop(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.getOrderForShop(customUserDetails.getUserId());
        List<ShopOrderResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            ShopOrderResponse order = new ShopOrderResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.getOrderStatus());
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
        List<OrderDetail> list = orderDetailSevice.findAllOrderByStatsus(customUserDetails.getUserId(),0);
        List<ShopOrderResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            ShopOrderResponse order = new ShopOrderResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.getOrderStatus());
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
    @GetMapping("shop/getDeliveringOrder")
    public ResponseEntity<?> getSoldOrder(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllOrderByStatsus(customUserDetails.getUserId(),1);
        List<ShopOrderResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            ShopOrderResponse order = new ShopOrderResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.getOrderStatus());
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
    @GetMapping("shop/successOrder")
    public ResponseEntity<?> successOrder(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllOrderByStatsus(customUserDetails.getUserId(),2);
        List<ShopOrderResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            ShopOrderResponse order = new ShopOrderResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.getOrderStatus());
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
    @GetMapping("shop/getShopCancelOrder")
    public ResponseEntity<?> cancelOrder(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllOrderByStatsus(customUserDetails.getUserId(),3);
        List<ShopOrderResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            ShopOrderResponse order = new ShopOrderResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.getOrderStatus());
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
    @GetMapping("shop/getUserCancelOrder")
    public ResponseEntity<?> getUserCancelOrder(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderDetail> list = orderDetailSevice.findAllOrderByStatsus(customUserDetails.getUserId(),4);
        List<ShopOrderResponse> listResponse = new ArrayList<>();
        for (OrderDetail orderDetail :list) {
            ShopOrderResponse order = new ShopOrderResponse();
            order.setOrderId(orderDetail.getOderDetailId());
            order.setProductName(orderDetail.getProductDetail().getProduct().getProductName());
            order.setOrderStatus(orderDetail.getOrderStatus());
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
            for (int i = 0; i < orderRequest.getListCart().size(); i++) {
                OrderDetail order = new OrderDetail();
                Cart cart = cartSevice.findById(orderRequest.getListCart().get(i));
                order.setProductDetail(cart.getProductDetail());
                order.setPrice(cart.getProductDetail().getPrice());
                order.setUsers(users);
                order.setQuantity(orderRequest.getListQuantity().get(i));
                order.setTotalPrice(order.getPrice()*order.getQuantity());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dateNow = new Date();
                String strNow = sdf.format(dateNow);
                order.setCreateDate(sdf.parse(strNow));
                order.setOrderStatus(0);
                Address address = addressSevice.findById(orderRequest.getAddressId());
                order.setFullName(address.getFullName());
                order.setAddress(address.getAddress());
                order.setPhoneNumber(address.getPhoneNumber());
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
                orderDetail.setOrderStatus(1);
                orderDetail = orderDetailSevice.save(orderDetail);
            }

        }catch (Exception e){
            check = false;
            e.printStackTrace();
        }
        if (check){
            List<Bill> listBill = new ArrayList<>();
            try {
                for (int id :confirm.getListOrderId()) {
                    OrderDetail orderDetail = orderDetailSevice.findById(id);
//                    orderDetail.getProductDetail().setQuantity(orderDetail.getProductDetail().getQuantity()-orderDetail.getQuantity());
//                    orderDetail.getProductDetail().setSoldQuantity(orderDetail.getProductDetail().getSoldQuantity()+orderDetail.getQuantity());
//                    productDetailSevice.saveOrUpdate(orderDetail.getProductDetail());
                    Bill bill = new Bill();
                    bill.setAddress(orderDetail.getAddress());
                    bill.setPhoneNumber(orderDetail.getPhoneNumber());
                    bill.setFullName(orderDetail.getFullName());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateNow = new Date();
                    String strNow = sdf.format(dateNow);
                    bill.setCreateDate(sdf.parse(strNow));
                    bill.setPrice(orderDetail.getPrice());
                    bill.setQuantity(orderDetail.getQuantity());
                    bill.setTotalPrice(orderDetail.getTotalPrice());
                    bill.setProductDetail(orderDetail.getProductDetail());
                    bill = billSevice.save(bill);
                    listBill.add(bill);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            List<BillResponse> listBillRespose = new ArrayList<>();
            for (Bill bill :listBill) {
                BillResponse billResponse = new BillResponse();
                billResponse.setBillId(bill.getBillId());
                billResponse.setCreateDate(bill.getCreateDate());
                billResponse.setPrice(bill.getPrice());
                billResponse.setQuantity(bill.getQuantity());
                billResponse.setTotalPrice(bill.getTotalPrice());
                billResponse.setAddress(bill.getAddress());
                billResponse.setFullName(bill.getFullName());
                billResponse.setPhoneNumber(bill.getPhoneNumber());
                billResponse.setProductName(bill.getProductDetail().getProduct().getProductName());
                billResponse.setColorName(bill.getProductDetail().getColor().getColorName());
                billResponse.setSizeName(bill.getProductDetail().getSize().getSizeName());
                listBillRespose.add(billResponse);
            }
            return ResponseEntity.ok(listBillRespose);
        }else {
            return ResponseEntity.ok("Xác nhận đơn hàng thất bại!");
        }
    }
    @PutMapping("confirmDelivery")
    public ResponseEntity<?> confirmDelivery(@RequestParam int orderId){
        try {
            OrderDetail order = orderDetailSevice.findById(orderId);
            order.setOrderStatus(2);
            order = orderDetailSevice.save(order);
            order.getProductDetail().setQuantity(order.getProductDetail().getQuantity() - order.getQuantity());
            order.getProductDetail().setSoldQuantity(order.getProductDetail().getSoldQuantity() + order.getQuantity());
            productDetailSevice.saveOrUpdate(order.getProductDetail());
            return ResponseEntity.ok("Xác nhận đơn hàng thành công!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Xác nhận đơn hàng thất bại!");
        }
    }
    @PutMapping("user/cancelDelivery")
    public ResponseEntity<?> cancelDelivery(@RequestParam int orderId){
        OrderDetail order = orderDetailSevice.findById(orderId);
        if (order.getOrderStatus()==1){
            boolean check = true;
            try {
                order.setOrderStatus(4);
                order = orderDetailSevice.save(order);
            }catch (Exception e){
                check = false;
                e.printStackTrace();
            }
            if (check){
                return ResponseEntity.ok("Bom hàng thành công!");
            }else {
                return ResponseEntity.ok("Hủy đơn hàng thất bại!");
            }
        }else {
            return ResponseEntity.ok("Đơn hàng không phù hợp vui lòng nhập lại!");
        }
    }
    @PutMapping("shop/cancelDelivery")
    public ResponseEntity<?> shopCancelDelivery(@RequestParam int orderId){
        OrderDetail order = orderDetailSevice.findById(orderId);
        if (order.getOrderStatus()==0){
            boolean check = true;
            try {
                order.setOrderStatus(3);
                order = orderDetailSevice.save(order);
            }catch (Exception e){
                check = false;
                e.printStackTrace();
            }
            if (check){
                return ResponseEntity.ok("Bom hàng thành công!");
            }else {
                return ResponseEntity.ok("Hủy đơn hàng thất bại!");
            }
        }else {
            return ResponseEntity.ok("Đơn hàng không phù hợp vui lòng nhập lại!");
        }
    }

}
