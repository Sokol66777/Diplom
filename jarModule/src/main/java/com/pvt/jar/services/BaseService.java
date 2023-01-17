package com.pvt.jar.services;


import com.pvt.jar.exceptions.LogicException;
import com.pvt.jar.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public class BaseService<T,ID> implements IService<T, ID>{

    @Autowired
    private BaseRepository<T,ID> baseRepository;


    @Override
    @Transactional
    public void add(T t) throws LogicException {

        baseRepository.save(t);
    }

    @Override
    @Transactional
    public void delete(ID id) {

        baseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void modify(T t) throws LogicException {

        baseRepository.save(t);
    }

    @Override
    public T get(ID id) {

        return baseRepository.findById(id).orElse(null);
    }
}
