package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Size;
import ra.model.repository.SizeRepository;
import ra.model.service.SizeSevice;
@Service
public class SIzeSeviceImp implements SizeSevice {
    @Autowired
    private SizeRepository sizeRepository;
    @Override
    public Size findById(int id) {
        return sizeRepository.findById(id).get();
    }
}
