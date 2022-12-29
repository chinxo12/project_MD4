package ra.payload.response;

import lombok.Data;

@Data
public class ProductDetailResponse {
    private int productDetailId;
    private String sizeName;
    private String colorName;
    private float price;
    private int quantity;
    private boolean productDetailStatus;
    private int soldQuantity;
}
