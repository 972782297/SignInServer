package com.njust.dao.impl;

import com.njust.dao.BaseDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(
        propagation= Propagation.REQUIRES_NEW,
        isolation = Isolation.SERIALIZABLE
)
@Scope("prototype")
public class BaseDAOImpl implements BaseDAO {
    @Autowired
    private SessionFactory sf;


    @Override
    public boolean add(Object o) {
        try {
            sf.getCurrentSession().save(o);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            sf.getCurrentSession().getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Object o) {
        try {
            sf.getCurrentSession().delete(o);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            sf.getCurrentSession().getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean update(Object o) {
        try {
            sf.getCurrentSession().update(o);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            sf.getCurrentSession().getTransaction().rollback();
            return false;
        }
    }

    @Override
    public List find(String hql) {
        return sf.getCurrentSession().createQuery(hql).list();
    }
}