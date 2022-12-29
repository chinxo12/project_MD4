package ra.payload.request;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductRequest {
    private String productName;
    private String discription;
    private int catalogId;
    private String productImage;
    private Set<String> listImage = new HashSet<>();
    private List<Integer> listSize;
    private List<Integer> listColor;
}
