package com.example.dao;

import com.example.entity.Story;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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
}
