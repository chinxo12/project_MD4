package ra.model.service;

import ra.model.entity.Cart;

import java.util.List;

public interface CartSevice {
    Cart insertCard(Cart cart);
    List<Cart> getAllCart(int id,int page,int size);

    void deleteCart(int id);
    int getTotalList(int id,int size);
    Cart findById(int id);
    List<Cart> findAll(int id);


}
