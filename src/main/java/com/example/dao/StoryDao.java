package com.example.dao;

import com.example.entity.Story;
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
public class StoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Story story) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(story);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.flush();
    }

    public List<Story> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Story s";
        Query<Story> query = session.createQuery(hql);
        return query.list();
    }
}
