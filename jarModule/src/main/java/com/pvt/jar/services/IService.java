package com.pvt.jar.services;

import com.pvt.jar.exceptions.LogicException;

public interface IService <T,ID>{

    void add(T t) throws LogicException;

    void delete(ID id);

    void modify(T t) throws LogicException;

    T get(ID id);
}
