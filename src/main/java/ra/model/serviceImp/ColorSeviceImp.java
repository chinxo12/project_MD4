package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Color;
import ra.model.repository.ColorRepository;
import ra.model.service.ColorSevice;
@Service
public class ColorSeviceImp implements ColorSevice {
    @Autowired
    private ColorRepository colorRepository;
    @Override
    public Color findById(int id) {
        return colorRepository.findById(id).get();
    }
}
