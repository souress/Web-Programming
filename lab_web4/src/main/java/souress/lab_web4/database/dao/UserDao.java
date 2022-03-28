package souress.lab_web4.database.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import souress.lab_web4.database.hibernate_util.UserSessionFactoryManager;
import souress.lab_web4.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserDao {
    @EJB
    private UserSessionFactoryManager userSessionFactoryManager;

    public void saveUser(User user) {
        Session session = userSessionFactoryManager.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public User findUser(String login) {
        Session session = userSessionFactoryManager.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query<User> userQuery = session.createQuery("FROM User u WHERE u.login = ?1", User.class).setParameter(1, login);
        User user = userQuery.uniqueResult();
        tx1.commit();
        session.close();
        return user;
    }
}
