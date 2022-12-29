package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Product;
import ra.model.repository.ProductDetailRepository;
import ra.model.repository.ProductRepository;
import ra.model.service.ProductSevice;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSeviceImp implements ProductSevice<Product, Integer> {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAll() {

        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }


    @Override
    public List<Product> getAllByUserId(int id) {
        return productRepository.findByUsers_UserIdAndAndProductStatus(id, true);
    }

    @Override
    public List<Product> getAllProductPagin(int id, int page, int size) {
        return productRepository.getProductPagin(id, page, size);
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepository.searchByProductNameContainsIgnoreCase(name);
    }

    @Override
    public List<Product> sortByName(String direction, int userId,int page,int size) {
        if (direction.equals("asc")) {
            return productRepository.softByNameForAdminASC(userId,page,size);
        } else {
            return productRepository.softByNameForAdminDESC(userId,page,size);
        }

    }

    @Override
    public List<Product> sortByNameAndPrice(String directionName, String directionPrice, int id,int page,int size) {
        List<Product> listProduct = new ArrayList<>();
        if (directionName.equalsIgnoreCase("asc")) {
            if (directionPrice.equalsIgnoreCase("asc")) {
                listProduct = productRepository.softByNameAndPriceNameAscPriceAsc(id,page,size);
            } else {
                listProduct = productRepository.softByNameAndPriceNameAscPriceDESC(id,page,size);
            }
        } else {
            if (directionPrice.equalsIgnoreCase("asc")) {
                listProduct = productRepository.softByNameAndPriceNameDESCPriceAsc(id,page,size);
            } else {
                listProduct = productRepository.softByNameAndPriceNameDESCPriceDESC(id,page,size);
            }
        }
        return listProduct;
    }

    @Override
    public List<Product> sortByPrice(String direction, int userId,int page,int size) {
        if (direction.equals("asc")) {
            return productRepository.softByPriceASC(userId,page,size);
        } else {
            return productRepository.softByPriceDESC(userId,page,size);

        }


    }

    @Override
    public int getTotalList(int id, int size) {
        return productRepository.totalPage(id,size);
    }

    @Override
    public Product getByProductDetailId(int id) {
        return productRepository.getProductByProducDetailId(id);
    }
}
