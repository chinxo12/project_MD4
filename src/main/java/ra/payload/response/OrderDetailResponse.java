package ra.payload.response;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDetailResponse {
    private int cartId;
    private String productName;
    private int quantity;
    private float price;
    private float totalPrice;
    private String sizeName;
    private String ColorName;
    private Date createDate;
    private boolean orderStatus;
    private String shopName;
}
