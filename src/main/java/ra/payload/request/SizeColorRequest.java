package ra.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class SizeColorRequest {
    private List<Integer> listSize;
    private List<Integer> listColor;
}
