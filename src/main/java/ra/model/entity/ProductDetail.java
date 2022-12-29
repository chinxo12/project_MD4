package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productdetail")
@Data
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productDetailID")
    private int productDetailId;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "sellQuantity")
    private int soldQuantity;
    @Column(name = "price")
    private float price;
    @Column(name = "productDetailStatus")
    private boolean productDetailStatus;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sizeId")
    private Size size;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "colorId")
    private Color color;
    @OneToMany(mappedBy = "productDetail")
    List<FeedBack> listFeedBack = new ArrayList<>();
}
