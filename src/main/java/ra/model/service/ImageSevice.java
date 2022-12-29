package ra.model.service;

import ra.model.entity.Image;

public interface ImageSevice {
    Image save(Image image);
    Image findByImageLink(String imageLink);
    void deleteById(int id);
    void deleteAllByProductId(int id);
}
