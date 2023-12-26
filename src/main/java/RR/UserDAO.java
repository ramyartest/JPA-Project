
package RR;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        }
    }

    public User getUserById(long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, userId);
        }
    }

    public void deleteUser(long userId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User userToDelete = session.get(User.class, userId);
            if (userToDelete != null) {
                session.delete(userToDelete);
            }
            session.getTransaction().commit();
        }
    }
}
