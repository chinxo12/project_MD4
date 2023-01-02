package ra.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill")
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billId")
    private int billId;
    @Column(name = "createDate")
    private Date createDate;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "address")
    private String address;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private float price;
    @Column(name = "totalPrice")
    private float totalPrice;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productDetailId")
    private ProductDetail productDetail;
}
