package ra.payload.response;

import lombok.Data;

@Data
public class CartRespose {
    private int cartId;
    private String productName;
    private int quantity;
    private float price;
    private float totalPrice;
    private String sizeName;
    private String ColorName;
}
