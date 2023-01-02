package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.entity.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    List<OrderDetail> findByUsers_UserId(int id);
    List<OrderDetail> findByUsers_UserIdAndAndOrderStatus(int id,int status);

    @Query(value = "select o.oderDetailId, o.userId,o.productDetailId,o.price,o.quantity,o.createDate,o.orderStatus,o.totalPrice,o.address,o.fullName,o.phoneNumber\n" +
            "from orderdetail o join productdetail p on p.productDetailID = o.productDetailId\n" +
            "join product p2 on p2.productId = p.productId join user  on user.userId = p2.userId\n" +
            "where user.userId = :uId",nativeQuery = true)
    List<OrderDetail> getOrderForShop(@Param("uId") int id);

    @Query(value = "select o.oderDetailId, o.userId,o.productDetailId,o.price,o.quantity,o.createDate,o.orderStatus,o.totalPrice,o.address,o.fullName,o.phoneNumber\n" +
            "from orderdetail o join productdetail p on p.productDetailID = o.productDetailId\n" +
            "join product p2 on p2.productId = p.productId join user  on user.userId = p2.userId\n" +
            "where user.userId = :uId and o.orderStatus = :status",nativeQuery = true)
    List<OrderDetail> getOrderForShopByStatus(@Param("uId") int id,@Param("status") int status);
    @Query(value = "select o.oderDetailId, o.userId,o.productDetailId,o.price,o.quantity,o.createDate,o.orderStatus,o.totalPrice,o.address,o.fullName,o.phoneNumber " +
            "from orderdetail o join productdetail p on p.productDetailID = o.productDetailId " +
            "where p.productId = :pId and o.userId = :uId",nativeQuery = true)
    OrderDetail findByUsers_UserIdAndProductId(@Param("uId")int userId,@Param("pId") int proId);
}
