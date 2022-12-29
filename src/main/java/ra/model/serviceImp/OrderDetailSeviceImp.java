package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.OrderDetail;
import ra.model.repository.OrderDetailRepository;
import ra.model.service.OrderDetailSevice;

import java.util.List;

@Service
public class OrderDetailSeviceImp implements OrderDetailSevice {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> findAll(int id) {
        return orderDetailRepository.findByUsers_UserId(id);
    }

    @Override
    public List<OrderDetail> findAllByStatus(int id, boolean status) {
          return orderDetailRepository.findByUsers_UserIdAndAndOrderStatus(id,status);
    }

    @Override
    public OrderDetail findById(int id) {
        return orderDetailRepository.findById(id).get();
    }

    @Override
    public List<OrderDetail> getOrderForShop(int id) {
        return orderDetailRepository.getOrderForShop(id);
    }

    @Override
    public List<OrderDetail> findAllOrderByStatsus(int id, boolean status) {
        return orderDetailRepository.getOrderForShopByStatus(id,status);
    }


}
