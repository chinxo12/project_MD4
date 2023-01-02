package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Address;
import ra.model.repository.AddressRepository;
import ra.model.service.AddressSevice;
@Service
public class AddressSeviceImp implements AddressSevice {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveOrUpdate(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address findById(int id) {
        return addressRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        addressRepository.deleteById(id);
    }
}
