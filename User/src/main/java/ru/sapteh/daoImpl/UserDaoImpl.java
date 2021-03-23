package ru.sapteh.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.Dao;
import ru.sapteh.models.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class UserDaoImpl implements Dao<User, Integer> {
    private final SessionFactory factory;

    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public List<User> readByAll() {
        try (Session session = factory.openSession()){
            Query<User> query = session.createQuery("FROM User");
            return query.list();
        }
    }

    public void create(User user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction();
        }
    }

    public void update(User user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction();
        }
    }

    public void delete(User user) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(user);
            session.getTransaction();
        }
    }
}
