package DAO;

import Bean.Profile;
import org.hibernate.Transaction;

import javax.persistence.Query;

public class ProfileDAO extends HibernateDAO {

    public Profile getProfileByUsername(String username){
        String hql = "From Profile as p Where username = :username";
        //Transaction transaction = getSession().beginTransaction();
        Query query = getSession().createQuery(hql,Profile.class).setParameter("username",username);
        return (Profile) query.getResultList().get(0);
    }

    public boolean authentication (String username, String password){
        String hql = "From Profile as p Where username = :username And password = :password";
        //Transaction transaction = getSession().beginTransaction();
        Query query = getSession().createQuery(hql,Profile.class).setParameter("username",username).setParameter("password", password);
        if(query.getResultList().size() == 0){
            return false;
        }else {
            return true;
        }
    }

    public boolean existUsername(String username){
        try {
            String hql = "From Profile as p Where username = :username";
            //Transaction transaction = getSession().beginTransaction();
            Query query = getSession().createQuery(hql, Profile.class).setParameter("username", username);
            if (query.getResultList().size() == 0) {
                return false;
            } else {
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }
}
