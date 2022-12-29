package ra.model.service;

import java.util.List;

public interface Sevices <T,E>{
    List<T> getAll();
    T save(T t);
    void delete(E id);
    T findById(int id);

}
