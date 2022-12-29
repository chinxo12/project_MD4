package ra.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "productId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(name = "productImage")
    private String productImage;
    @Column(name = "discription")
    private String discription;
    @Column(name = "productName")
    private String productName;
    @Column(name = "productStatus")
    private boolean productStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catalogId")
    private Catalog catalog;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private Users users;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "product")
    Set<Image> listImage = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "product")
    List<ProductDetail> listProductDetail = new ArrayList<>();
}
