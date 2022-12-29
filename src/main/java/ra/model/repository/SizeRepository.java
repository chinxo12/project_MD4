package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Size;
@Repository
public interface SizeRepository extends JpaRepository<Size,Integer> {
}
