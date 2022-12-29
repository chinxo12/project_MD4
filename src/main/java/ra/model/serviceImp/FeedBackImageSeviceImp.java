package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Image;
import ra.model.entity.ImageFeedBack;
import ra.model.repository.FeedBackImageRepository;
import ra.model.service.FeedBackImageSevice;
import ra.model.service.FeedBackSevice;

import java.util.List;
@Service
public class FeedBackImageSeviceImp implements FeedBackImageSevice {
    @Autowired
    private FeedBackImageRepository feedBackImageRepository;
    @Override
    public ImageFeedBack save(ImageFeedBack feedBackSevice) {
        return feedBackImageRepository.save(feedBackSevice);
    }

    @Override
    public void delete(List<ImageFeedBack> list) {
        feedBackImageRepository.deleteAll(list);
    }


}
