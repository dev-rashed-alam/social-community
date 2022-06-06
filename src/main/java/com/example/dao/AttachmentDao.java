package com.example.dao;

import com.example.entity.Attachment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public class AttachmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Attachment attachment) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(attachment);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

        session.flush();
    }

    public Attachment findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        try {
            String hql = "FROM Attachment a where a.id=:id";
            Query<Attachment> attachmentQuery = session.createQuery(hql);
            attachmentQuery.setParameter("id", id);
            return attachmentQuery.getSingleResult();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return null;
    }
}
