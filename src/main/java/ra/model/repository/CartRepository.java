package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.entity.Cart;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(value = "select c.cartId,c.userId,c.productDetailId,c.quantity,c.totalPrice from cart c where c.userId= :uId" +
            " order by c.cartId limit :size offset :page",nativeQuery = true)
    List<Cart> getAllCart(@Param("uId")int id,@Param("page")int page,@Param("size") int size);

    @Query(value = "select ceil(count(cartId)/:size1) from cart p where p.userId= :uId",nativeQuery = true)
    int totalPage(@Param("uId")int id,@Param("size1")int size);
}
