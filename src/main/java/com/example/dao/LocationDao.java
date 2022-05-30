package com.example.dao;

import com.example.entity.Location;
import com.example.model.LocationModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class LocationDao {
    @Autowired
    SessionFactory sessionFactory;

    public void save(LocationModel locationModel) {
        Session session = sessionFactory.getCurrentSession();
        Location location = new Location();
        location.setLocationName(locationModel.getLocationName());
        try {
            System.out.println(location);
            session.save(location);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.flush();
    }

    public List<Location> locations() {
        String hql = "FROM Location l";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        List<Location> locations = (List<Location>)query.list();
        return locations;
    }
}
