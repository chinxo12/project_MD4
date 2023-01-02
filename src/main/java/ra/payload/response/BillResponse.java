package ra.payload.response;

import lombok.Data;

import java.util.Date;

@Data
public class BillResponse {
    private int billId;
    private String fullName;
    private String address;
    private String phoneNumber;
    private Date createDate;
    private String productName;
    private String colorName;
    private String sizeName;
    private int quantity;
    private float price;
    private float totalPrice;

}
