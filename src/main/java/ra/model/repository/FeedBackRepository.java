package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.FeedBack;
@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack,Integer> {
}
