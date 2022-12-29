package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Cart;
import ra.model.repository.CartRepository;
import ra.model.service.CartSevice;

import java.util.List;
@Service
public class CartSeviceImp implements CartSevice {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public Cart insertCard(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCart(int id,int page,int size) {
        return cartRepository.getAllCart(id,page,size);
    }

    @Override
    public void deleteCart(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public int getTotalList(int id, int size) {
        return cartRepository.totalPage(id,size);
    }

    @Override
    public Cart findById(int id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public List<Cart> findAll(int id) {
        return cartRepository.findByUsers_UserId(id);
    }
}
