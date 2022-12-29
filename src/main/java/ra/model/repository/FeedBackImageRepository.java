package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.ImageFeedBack;

@Repository
public interface FeedBackImageRepository extends JpaRepository<ImageFeedBack,Integer> {
}
