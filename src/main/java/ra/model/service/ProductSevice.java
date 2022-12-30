package ra.model.service;

import ra.model.entity.Product;

import java.util.List;

public interface ProductSevice<T,E> extends Sevices<T,E>{
  List<T> getAllByUserId(int id);
  List<T> getAllProductPagin(int id,int page,int size);
  List<T> searchByName(String name);
  List<T> sortByName(String direction,int userId,int page,int size);
  List<T> sortByNameAndPrice(String direction, String direction2,int id,int page,int size);

  List<T> sortByPrice(String direction,int userId,int page,int size);

  int getTotalList(int id,int size);
  T getByProductDetailId(int id);

  List<T> findAllByCatalogId(int catalogId,int page,int size);
  int getTotalPageForFindByCatalog(int catalogId,int size);

  List<T> getAllWishList(int userId);

}
