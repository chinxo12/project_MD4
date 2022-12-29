package ra.model.service;

import ra.model.entity.Image;
import ra.model.entity.ImageFeedBack;

import java.util.List;

public interface FeedBackImageSevice {
    ImageFeedBack save(ImageFeedBack feedBackSevice);
    void delete(List<ImageFeedBack> list);
}
