package com.njust.dao;

import java.util.List;

public interface BaseDAO {
    boolean add(Object o);

    boolean delete(Object o);

    boolean update(Object o);

    List find(String hql);
}
