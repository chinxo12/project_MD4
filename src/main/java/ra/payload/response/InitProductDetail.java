package ra.payload.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InitProductDetail {
    private List<Integer> listSize = new ArrayList<>();
    private List<Integer> listColor = new ArrayList<>();
}
