package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Image;
@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {
    Image findByImageLink(String imageLink);
    void deleteAllByProduct_ProductId(int id);
}
