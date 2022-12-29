package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.ProductDetail;
import ra.model.repository.ProductDetailRepository;
import ra.model.repository.ProductRepository;
import ra.model.service.ProductDetailSevice;

import java.util.List;

@Service
public class ProductDetailSeviceImp implements ProductDetailSevice {
    @Autowired
    private ProductDetailRepository productDetailRepository;


    @Override
    public ProductDetail saveOrUpdate(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public void deleteProductDetail(int id) {
        productDetailRepository.deleteById(id);
    }

    @Override
    public void saveAllProductDetail(List<ProductDetail> list) {
        productDetailRepository.saveAll(list);
    }

    @Override
    public void deleteAllByProductId(int proId) {
        productDetailRepository.deleteAllByProduct_ProductId(proId);
    }

    @Override
    public ProductDetail findById(int id) {
        return productDetailRepository.findById(id).get();
    }
}
