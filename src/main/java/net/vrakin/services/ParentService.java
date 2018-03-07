package net.vrakin.services;

import net.vrakin.exceptions.ParentServiceException;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface ParentService<T, ID extends Serializable> {
    public default Iterable<T> findAll() {
        return getRepository().findAll();
    }

    public default T get(ID id) {
        return getRepository().findOne(id);
    }

    public default T save(T entity) {
        return getRepository().save(entity);
    }

    public default void delete(ID id) {
        if (getRepository().exists(id)) {
            getRepository().delete(id);
        }
        else {
            throw new ParentServiceException("'id' doesn't exists: " + id);
        }
    }

    public default void update(T entity) {
        if (getRepository().exists(getId(entity))) {
            getRepository().save(entity);
        }
        else {
            throw new ParentServiceException("Can't update because it doesn't exist in DB: " + entity);
        }
    }

    public ID getId(T entity);

    public CrudRepository<T, ID> getRepository();
}

