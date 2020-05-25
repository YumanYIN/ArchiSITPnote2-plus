package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateDAO {

    protected Session SESSION;

    protected Session getSession() {
        if (SESSION == null || ! SESSION.isOpen()) {
            SESSION = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
        }
        return SESSION;
    }

    public <T> boolean addEntity(T t){

        try{
            Transaction transaction = getSession().beginTransaction();
            getSession().save(t);
            transaction.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public <T> boolean delEntity(T t){
        try{
            Transaction transaction = getSession().beginTransaction();
            getSession().remove(t);
            transaction.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public <T> T findById(Class<T> tClass, int id){
        return getSession().get(tClass,id);
    }

    public <T> List<T> findAll(Class<T> tClass){
        String hql = "from " + tClass.getSimpleName();
        Query query = getSession().createQuery(hql);
        return (List<T>) query.getResultList();
    }

    public <T> List<T> find(Class<T> tClass, String hql){
        Query query = getSession().createQuery(hql);
        return (List<T>) query.getResultList();
    }
}
