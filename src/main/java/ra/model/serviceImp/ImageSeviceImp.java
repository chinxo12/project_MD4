package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Image;
import ra.model.repository.ImageRepository;
import ra.model.service.ImageSevice;
@Service
public class ImageSeviceImp implements ImageSevice {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image findByImageLink(String imageLink) {
        return imageRepository.findByImageLink(imageLink);
    }

    @Override
    public void deleteById(int id) {
        imageRepository.deleteById(id);
    }

    @Override
    public void deleteAllByProductId(int id) {
        imageRepository.deleteAllByProduct_ProductId(id);
    }
}
