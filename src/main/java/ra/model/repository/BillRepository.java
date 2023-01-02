package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
}
