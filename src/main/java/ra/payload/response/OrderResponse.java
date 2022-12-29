package ra.payload.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {
    private int orderId;
    private Date createDate;
    private float totalMoney;
    private float discount;
    private float payMoney;
    private boolean orderStatus;
    List<OrderDetailResponse> listOrderDetail = new ArrayList<>();

}
