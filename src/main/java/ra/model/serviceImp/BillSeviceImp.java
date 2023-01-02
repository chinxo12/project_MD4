package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Bill;
import ra.model.repository.BillRepository;
import ra.model.service.BillSevice;

@Service
public class BillSeviceImp implements BillSevice {
    @Autowired
    private BillRepository billRepository;
    @Override
    public Bill findById(int id) {
        return billRepository.findById(id).get();
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }
}
