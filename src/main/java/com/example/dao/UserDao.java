package com.example.dao;

import com.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        session.flush();
    }

    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User u";
        Query<User> query = session.createQuery(hql);
        return query.list();
    }

    public User findByUserEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User u where u.email=:email";
        Query<User> userQuery = sessionFactory.getCurrentSession().createQuery(hql);
        userQuery.setParameter("email", email);
        return userQuery.getSingleResult();
    }

}
