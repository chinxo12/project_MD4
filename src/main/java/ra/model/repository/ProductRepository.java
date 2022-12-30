package ra.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByUsers_UserIdAndAndProductStatus(int id,boolean status);
    @Query(value = "select o.productId,o.productName,o.discription,o.productStatus,o.productImage,o.userId,o.catalogId" +
            " from product o where o.userId = :uId order by o.productId limit :size1 offset :page",nativeQuery = true)
    List<Product> getProductPagin(@Param("uId")int id,@Param("page")int page,@Param("size1")int size);

    List<Product> searchByProductNameContainsIgnoreCase(String str);

    @Query(value = "select o.productId,o.productName,o.discription,o.productStatus,o.productImage,o.userId,o.catalogId " +
            " from product o where o.userId = :uId order by o.productId desc limit :size1 offset :page",nativeQuery = true)
    List<Product> softByNameForAdminDESC(@Param("uId")int id,@Param("page")int page,@Param("size1")int size);

    @Query(value = "select o.productId,o.productName,o.discription,o.productStatus,o.productImage,o.userId,o.catalogId " +
            "from product o where o.userId = :uId order by o.productId asc limit :size1 offset :page",nativeQuery = true)
    List<Product> softByNameForAdminASC(@Param("uId")int id,@Param("page")int page,@Param("size1")int size);

    @Query(value = "select p.productId, p.productImage, p.discription, p.userId, p.catalogId, p.productStatus,p.productName" +
            " from product p join productdetail p2 on p.productId = p2.productId where userId = :uId" +
            " group by p.productId order by p.productName,min(p2.price) limit :size1 offset :page",nativeQuery = true)
    List<Product> softByNameAndPriceNameAscPriceAsc(@Param("uId")int id,@Param("page")int page,@Param("size1")int size);


    @Query(value = "select p.productId, p.productImage, p.discription, p.userId, p.catalogId, p.productStatus,p.productName" +
            " from product p join productdetail p2 on p.productId = p2.productId where userId = :uId" +
            " group by p.productId order by  p.productName,min(p2.price) desc limit :size1 offset :page ",nativeQuery = true)
    List<Product> softByNameAndPriceNameAscPriceDESC(@Param("uId")int id,@Param("page")int page,@Param("size1")int size);


    @Query(value = "select p.productId, p.productImage, p.discription, p.userId, p.catalogId, p.productStatus,p.productName" +
            " from product p join productdetail p2 on p.productId = p2.productId where userId = :uId" +
            " group by p.productId order by p.productName desc ,min(p2.price) limit :size1 offset :page",nativeQuery = true)
    List<Product> softByNameAndPriceNameDESCPriceAsc(@Param("uId")int id,@Param("page")int page,@Param("size1")int size);

    @Query(value = "select p.productId, p.productImage, p.discription, p.userId, p.catalogId, p.productStatus,p.productName" +
            " from product p join productdetail p2 on p.productId = p2.productId where userId = :uId" +
            " group by p.productId order by p.productName desc ,min(p2.price) desc limit :size1 offset :page",nativeQuery = true)
    List<Product> softByNameAndPriceNameDESCPriceDESC(@Param("uId")int id,@Param("page")int page,@Param("size1")int size);
    @Query(value = "select p.productId, p.productImage, p.discription, p.userId, p.catalogId, p.productStatus,p.productName" +
            " from product p join productdetail p2 on p.productId = p2.productId where userId = :uId" +
            " group by p.productId order by min(p2.price) limit :size1 offset :page",nativeQuery = true)
    List<Product> softByPriceASC(@Param("uId")int id,@Param("page")int page,@Param("size1")int size);

    @Query(value = "select p.productId, p.productImage, p.discription, p.userId, p.catalogId, p.productStatus,p.productName" +
            " from product p join productdetail p2 on p.productId = p2.productId where userId = :uId" +
            " group by p.productId order by min(p2.price) desc limit :size1 offset :page",nativeQuery = true)
    List<Product> softByPriceDESC(@Param("uId")int id,@Param("page")int page,@Param("size1")int size);


    @Query(value = "select ceil(count(productId)/:size1) from product p where p.userId= :uId",nativeQuery = true)
    int totalPage(@Param("uId")int id,@Param("size1")int size);

    @Query(value = "select p.productId, p.productImage, p.discription, p.userId, p.catalogId, p.productStatus,p.productName " +
            "from product p join productdetail p2 on p.productId = p2.productId " +
            "where p2.productDetailID = :proId",nativeQuery = true)
    Product getProductByProducDetailId(@Param("proId")int proId);

    @Query(value = "select p.productId, p.productImage, p.discription, p.userId, p.catalogId, p.productStatus,p.productName\n" +
            "from product p join catalog c on c.catalogId = p.catalogId\n" +
            "where   p.catalogId  in  (WITH recursive TEMPDATA(catId, catName)\n" +
            "                                             AS (SELECT a.catalogId,\n" +
            "                                                        a.catalogName\n" +
            "                                                 FROM catalog a\n" +
            "                                                 WHERE catalogId = :cId\n" +
            "                                                 union all\n" +
            "                                                 select child.catalogId, child.catalogName\n" +
            "                                                 from TEMPDATA p\n" +
            "                                                          inner join catalog child on p.catId = child.parentId)\n" +
            "                          SELECT catId\n" +
            "                          FROM TEMPDATA )\n" +
            "group by p.productId\n" +
            "order by productId limit :size1 offset :page",nativeQuery = true)
    List<Product> findByCatalogId(@Param("cId")int catalogId,@Param("page")int page,@Param("size1")int size);
    @Query(value = "select ceil(count(p.productId)/ :size1)\n" +
            "from product p  join catalog c on c.catalogId = p.catalogId\n" +
            "where   p.catalogId  in  (WITH recursive TEMPDATA(catId, catName)\n" +
            "                                             AS (SELECT a.catalogId,\n" +
            "                                                        a.catalogName\n" +
            "                                                 FROM catalog a\n" +
            "                                                 WHERE catalogId = :cId\n" +
            "                                                 union all\n" +
            "                                                 select child.catalogId, child.catalogName\n" +
            "                                                 from TEMPDATA p\n" +
            "                                                          inner join catalog child on p.catId = child.parentId)\n" +
            "                          SELECT catId\n" +
            "                          FROM TEMPDATA )",nativeQuery = true)
    int getTotalPageForFindByCatalog(@Param("cId")int catId,@Param("size1")int size);
    @Query(value = "select * from product p join wishlist w on p.productId = w.productId where w.userId = :uId",nativeQuery = true)
    List<Product> getAllWishList(@Param("uId") int userId);
}
