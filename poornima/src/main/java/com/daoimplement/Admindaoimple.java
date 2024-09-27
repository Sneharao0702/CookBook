package com.daoimplement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.entitiy.Admin;
import com.helper.Helper;
import com.hibernate.dao.Admindao;


public class Admindaoimple implements Admindao {

    private SessionFactory sessionFactory;

    public Admindaoimple() {
        this.sessionFactory = Helper.getSessionFactory();
    }

    @Override
    public boolean login(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Admin WHERE username = :username AND password = :password";
            Query<Admin> query = session.createQuery(hql, Admin.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            Admin admin = query.uniqueResult();

            return admin != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

