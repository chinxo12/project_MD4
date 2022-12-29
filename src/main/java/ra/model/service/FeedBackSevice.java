package ra.model.service;

import ra.model.entity.FeedBack;

public interface FeedBackSevice {
    FeedBack saveOrUpdate(FeedBack feedBack);
    void deleteFeedBack(int id);
}
