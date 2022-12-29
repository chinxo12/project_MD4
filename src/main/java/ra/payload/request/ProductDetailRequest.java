package ra.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailRequest {
    private List<Integer> listSize;
    private List<Integer> listColor;
    private List<Integer> listQuantity;
    private List<Float> listPrice;
}
