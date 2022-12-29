package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Catalog;
import ra.model.repository.CatalogRepository;
import ra.model.service.CatalogSevice;

import java.util.List;
@Service
public class CatalogSeviceImp implements CatalogSevice<Catalog,Integer> {
    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public List<Catalog> getAll() {
        return null;
    }

    @Override
    public Catalog save(Catalog catalog) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Catalog findById(int id) {
        return catalogRepository.findById(id).get();
    }
}
