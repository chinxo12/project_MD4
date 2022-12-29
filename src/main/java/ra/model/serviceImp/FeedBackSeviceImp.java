package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.FeedBack;
import ra.model.repository.FeedBackRepository;
import ra.model.service.FeedBackSevice;
@Service
public class FeedBackSeviceImp implements FeedBackSevice {
    @Autowired
    private FeedBackRepository feedBackRepository;
    @Override
    public FeedBack saveOrUpdate(FeedBack feedBack) {
        return feedBackRepository.save(feedBack);
    }

    @Override
    public void deleteFeedBack(int id) {
        feedBackRepository.deleteById(id);
    }
}
