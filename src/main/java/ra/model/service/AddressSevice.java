package ra.model.service;

import ra.model.entity.Address;

public interface AddressSevice {
    Address saveOrUpdate(Address address);
    Address findById(int id);
    void delete(int id);
}
