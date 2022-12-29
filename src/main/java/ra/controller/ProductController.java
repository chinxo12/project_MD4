package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Catalog;
import ra.model.entity.Image;
import ra.model.entity.Product;
import ra.model.entity.ProductDetail;
import ra.model.repository.ProductDetailRepository;
import ra.model.service.*;
import ra.payload.request.ProductRequest;
import ra.payload.request.SizeColorRequest;
import ra.payload.response.DisplayProduct;
import ra.payload.request.ProductDetailRequest;
import ra.security.CustomUserDetails;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/auth/product")
public class ProductController {
    @Autowired
    private ProductSevice<Product,Integer> productSevice;
    @Autowired
    private CatalogSevice<Catalog,Integer> catalogSevice;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageSevice imageSevice;
    @Autowired
    private ProductDetailSevice productDetailSevicel;
    @Autowired
    private SizeSevice sizeSevice;
    @Autowired
    private ColorSevice colorSevice;


    @GetMapping("getAllProduct")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAll(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<DisplayProduct> listDisplayProduct = new ArrayList<>();
        List<Product> listProduct = productSevice.getAllByUserId(customUserDetails.getUserId());
        for (Product pro :listProduct) {
            DisplayProduct displayProduct = new DisplayProduct();
            displayProduct.setProductId(pro.getProductId());
            displayProduct.setProductName(pro.getProductName());
            displayProduct.setProductStatus(pro.isProductStatus());
            displayProduct.setCatalog(pro.getCatalog());
            displayProduct.getListProductDetail().addAll(pro.getListProductDetail());
            listDisplayProduct.add(displayProduct);
        }

        return ResponseEntity.ok(listDisplayProduct);
    }
    @PostMapping("createProduct")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> insertProduct(@RequestBody ProductRequest productRequest){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductImage(productRequest.getProductImage());
        product.setDiscription(productRequest.getDiscription());
        product.setProductStatus(true);
        product.setCatalog(catalogSevice.findById(productRequest.getCatalogId()));
        product.setUsers(userService.findById(customUserDetails.getUserId()));
        for (String str :productRequest.getListImage()) {
            Image image = new Image();
            image.setImageLink(str);
            image = imageSevice.save(image);
            product.getListImage().add(image);
        }
        try {
            product = productSevice.save(product);
            for (int i = 0; i < productRequest.getListSize().size(); i++) {
                for (int j = 0; j < productRequest.getListColor().size(); j++) {
                    ProductDetail productDetail = new ProductDetail();
                    productDetail.setSize(sizeSevice.findById(productRequest.getListSize().get(i)));
                    productDetail.setColor(colorSevice.findById(productRequest.getListColor().get(j)));
                    productDetail.setProduct(product);
                    productDetail = productDetailSevicel.saveOrUpdate(productDetail);
                }
            }
            return ResponseEntity.ok("them moi thanh cong!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Co loi trong qua trinh xu ly vui long thu lai!!!");
        }
    }
    @PostMapping("createProductDetail/{productId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createProductDetail(@RequestBody ProductDetailRequest proRequest,@PathVariable("productId") int productId){
        Product product = productSevice.findById(productId);
        for (int i = 0; i < product.getListProductDetail().size(); i++) {
            product.getListProductDetail().get(i).setPrice(proRequest.getListPrice().get(i));
            product.getListProductDetail().get(i).setQuantity(proRequest.getListQuantity().get(i));
        }
        try {
            productDetailSevicel.saveAllProductDetail(product.getListProductDetail());
            return ResponseEntity.ok("Thêm mới  thành công!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Thêm mới thất bại!");
        }
    }

    @PutMapping("updateProductDetail/{productId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateProductDetail(@PathVariable("productId") int productId,@RequestBody ProductDetailRequest proRequest){
        Product product = productSevice.findById(productId);
        for (int i = 0; i < product.getListProductDetail().size(); i++) {
            product.getListProductDetail().get(i).setPrice(proRequest.getListPrice().get(i));
            product.getListProductDetail().get(i).setQuantity(proRequest.getListQuantity().get(i));
        }
        try {
            productDetailSevicel.saveAllProductDetail(product.getListProductDetail());
            return ResponseEntity.ok("update thành công!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("update thất bại!");
        }

    }
    @PutMapping("updateProduct/{productId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateProduct(@PathVariable("productId") int productId,@RequestBody ProductRequest productRequest){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product product = productSevice.findById(productId);
        product.setProductName(productRequest.getProductName());
        product.setProductImage(productRequest.getProductImage());
        product.setDiscription(productRequest.getDiscription());
        product.setProductStatus(true);
        product.setCatalog(catalogSevice.findById(productRequest.getCatalogId()));
        product.setUsers(userService.findById(customUserDetails.getUserId()));
        imageSevice.deleteAllByProductId(productId);
        for (String str :productRequest.getListImage()) {
            Image image = new Image();
            image.setImageLink(str);
            image = imageSevice.save(image);
            product.getListImage().add(image);
        }
        try {
            product = productSevice.save(product);
            return ResponseEntity.ok("update thành công!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Có lỗi trong quá trình xử lý vui lòng thử lại!!!");
        }
    }
    @PutMapping("deleteProduct/{productId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") int productId){
        Product product = productSevice.findById(productId);
        product.setProductStatus(false);
        try {
            product = productSevice.save(product);
            return ResponseEntity.ok("Xóa thành công!");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Có lỗi trong quá trình xử lý vui lòng thử lại!!!");
        }
    }
    @PutMapping("deleteProduct/{productDetailId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteProductDetail(@PathVariable("productDetailId") int productDetailId){
        ProductDetail productDetail = productDetailSevicel.findById(productDetailId);
        productDetail.setProductDetailStatus(false);
        try {
            productDetailSevicel.saveOrUpdate(productDetail);
            return ResponseEntity.ok("Xóa thành công!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Có lỗi trong quá trình xử lý vui lòng thử lại!!!");
        }
    }
    @GetMapping("getAll")
    @PreAuthorize("hasRole('USER')")
    public  ResponseEntity<?> getAllProduct( @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "3") int size){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Product> listProduct= productSevice.getAllProductPagin(customUserDetails.getUserId(),page,size);
        List<DisplayProduct> list = new ArrayList<>();
        for (Product pro :listProduct) {
            DisplayProduct displayProduct = new DisplayProduct();
            displayProduct.setProductId(pro.getProductId());
            displayProduct.setProductName(pro.getProductName());
            displayProduct.setProductStatus(pro.isProductStatus());
            displayProduct.setCatalog(pro.getCatalog());
            displayProduct.getListProductDetail().addAll(pro.getListProductDetail());
            list.add(displayProduct);
        }
        return ResponseEntity.ok(listProduct);
    }
    @GetMapping("searchByName")
        public ResponseEntity<?> searchByName(@RequestParam("searchName") String name ){
        List<Product> listProduct = productSevice.searchByName(name);
        List<DisplayProduct> list = new ArrayList<>();
        for (Product pro :listProduct) {
            DisplayProduct displayProduct = new DisplayProduct();
            displayProduct.setProductId(pro.getProductId());
            displayProduct.setProductName(pro.getProductName());
            displayProduct.setProductStatus(pro.isProductStatus());
            displayProduct.setCatalog(pro.getCatalog());
            displayProduct.getListProductDetail().addAll(pro.getListProductDetail());
            list.add(displayProduct);
        }
        return ResponseEntity.ok(listProduct);
    }

    @GetMapping("softByName")
    public ResponseEntity<?> sortByName(@RequestParam("direction")String direction,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Product> listProduct = productSevice.sortByName(direction,customUserDetails.getUserId(),page,size);
        int totalPage = productSevice.getTotalList(customUserDetails.getUserId(),size);
        List<DisplayProduct> list = new ArrayList<>();
        for (Product pro :listProduct) {
            DisplayProduct displayProduct = new DisplayProduct();
            displayProduct.setProductId(pro.getProductId());
            displayProduct.setProductName(pro.getProductName());
            displayProduct.setProductStatus(pro.isProductStatus());
            displayProduct.setCatalog(pro.getCatalog());
            displayProduct.getListProductDetail().addAll(pro.getListProductDetail());
            list.add(displayProduct);
        }
        Map<String,Object> displayValue = new HashMap<>();
        displayValue.put("totalPage",totalPage);
        displayValue.put("listProduct",listProduct);

        return ResponseEntity.ok(displayValue);
    }

    @GetMapping("softByPrice")
    public ResponseEntity<?> sortByPrice(@RequestParam("direction")String direction,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "3") int size){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Product> listProduct = productSevice.sortByPrice(direction,customUserDetails.getUserId(),page,size);
        int totalPage = productSevice.getTotalList(customUserDetails.getUserId(),size);
        List<DisplayProduct> list = new ArrayList<>();
        for (Product pro :listProduct) {
            DisplayProduct displayProduct = new DisplayProduct();
            displayProduct.setProductId(pro.getProductId());
            displayProduct.setProductName(pro.getProductName());
            displayProduct.setProductStatus(pro.isProductStatus());
            displayProduct.setCatalog(pro.getCatalog());
            displayProduct.getListProductDetail().addAll(pro.getListProductDetail());
            list.add(displayProduct);
        }
        Map<String,Object> displayValue = new HashMap<>();
        displayValue.put("totalPage",totalPage);
        displayValue.put("listProduct",listProduct);

        return ResponseEntity.ok(displayValue);
    }

    @GetMapping("softByNameAndPrice")
    public ResponseEntity<?> sortByNameAndPrice(@RequestParam("directionName") String directionName,
                                                @RequestParam("directionPrice") String directionPrice,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "3") int size){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int totalPage = productSevice.getTotalList(customUserDetails.getUserId(),size);
        List<Product> listProduct = productSevice.sortByNameAndPrice(directionName,directionPrice,customUserDetails.getUserId(),page,size);
        List<DisplayProduct> list = new ArrayList<>();
        for (Product pro :listProduct) {
            DisplayProduct displayProduct = new DisplayProduct();
            displayProduct.setProductId(pro.getProductId());
            displayProduct.setProductName(pro.getProductName());
            displayProduct.setProductStatus(pro.isProductStatus());
            displayProduct.setCatalog(pro.getCatalog());
            displayProduct.getListProductDetail().addAll(pro.getListProductDetail());
            list.add(displayProduct);
        }
        Map<String,Object> displayValue = new HashMap<>();
        displayValue.put("totalPage",totalPage);
        displayValue.put("listProduct",listProduct);

        return ResponseEntity.ok(displayValue);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<?> getById(@PathVariable("productId") int id){
        Product pro = productSevice.findById(id);
        DisplayProduct displayProduct = new DisplayProduct();
        displayProduct.setProductId(pro.getProductId());
        displayProduct.setProductName(pro.getProductName());
        displayProduct.setProductStatus(pro.isProductStatus());
        displayProduct.setCatalog(pro.getCatalog());
        displayProduct.getListProductDetail().addAll(pro.getListProductDetail());
        return ResponseEntity.ok(displayProduct);
    }
}
