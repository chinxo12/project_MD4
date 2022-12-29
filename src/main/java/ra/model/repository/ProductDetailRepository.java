package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.ProductDetail;
@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Integer> {
    void deleteAllByProduct_ProductId(int proId);
}
