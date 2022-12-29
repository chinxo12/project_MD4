package ra.payload.response;

import lombok.Data;
import ra.model.entity.ImageFeedBack;

import java.util.ArrayList;
import java.util.List;

@Data
public class FeedBackResponse {
    private int star;
    private  String feedBack;
    private String userName;
    List<String> listImage = new ArrayList<>();
    private String colorName;
    private String SizeName;
}
