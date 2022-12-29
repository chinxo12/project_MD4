package ra.model.service;

import ra.model.entity.Product;

import java.util.List;

public interface ProductSevice<T,E> extends Sevices<T,E>{
  List<Product> getAllByUserId(int id);
  List<Product> getAllProductPagin(int id,int page,int size);
  List<Product> searchByName(String name);
  List<Product> sortByName(String direction,int userId,int page,int size);
  List<Product> sortByNameAndPrice(String direction, String direction2,int id,int page,int size);

  List<Product> sortByPrice(String direction,int userId,int page,int size);

  int getTotalList(int id,int size);
  T getByProductDetailId(int id);

}
