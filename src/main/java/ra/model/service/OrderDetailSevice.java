package ra.model.service;

import ra.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailSevice {
    OrderDetail save(OrderDetail orderDetail);
    List<OrderDetail> findAll(int id);
    List<OrderDetail> findAllByStatus(int id,int status);
    OrderDetail findById(int id);
    List<OrderDetail> getOrderForShop(int id);
    List<OrderDetail> findAllOrderByStatsus(int id,int status);
    OrderDetail findByProductIdAndUserId(int userId,int proId);
}
