package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Color;
@Repository
public interface ColorRepository extends JpaRepository<Color,Integer> {
}
