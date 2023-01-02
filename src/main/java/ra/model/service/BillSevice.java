package ra.model.service;

import ra.model.entity.Bill;

public interface BillSevice {
    Bill findById(int id);
    Bill save(Bill bill);
}
