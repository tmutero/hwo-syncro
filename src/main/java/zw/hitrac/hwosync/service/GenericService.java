package zw.hitrac.hwosync.service;

import java.io.Serializable;
import java.util.List;


/**
 * Created by tmutero
 */
public interface GenericService <T extends Serializable> extends Serializable{
    public T save(T t);
    public List<T> findAll();
    public T findOne(Long id);
    public Boolean checkDuplicate(T t);
    public void remove(T t);
}
