package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import ra.model.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
