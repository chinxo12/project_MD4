package ra.payload.request;

import lombok.Data;

import java.util.List;
@Data
public class ConfirmOrderRequest {
    private List<Integer> listOrderId;
}
