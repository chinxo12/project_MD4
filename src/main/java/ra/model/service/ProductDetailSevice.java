package ra.model.service;

import ra.model.entity.ProductDetail;

import java.util.List;

public interface ProductDetailSevice {
    ProductDetail saveOrUpdate(ProductDetail productDetail);
    void deleteProductDetail(int id);

    void saveAllProductDetail(List<ProductDetail> list);
    void deleteAllByProductId(int proId);
    ProductDetail findById(int id);

}
