package ra.payload.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FeedBackRequest {
    private String feedback;
    private List<String> listImage = new ArrayList<>();
    private int star;
}
