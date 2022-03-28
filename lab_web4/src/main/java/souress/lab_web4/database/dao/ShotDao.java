package souress.lab_web4.database.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import souress.lab_web4.database.hibernate_util.ShotSessionFactoryManager;
import souress.lab_web4.entities.Shot;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ShotDao {
    @EJB
    private ShotSessionFactoryManager shotSessionFactoryManager;


    public List<Shot> getShots(String login) {
        Session session = shotSessionFactoryManager.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query<Shot> resQuery = session.createQuery("FROM Shot r WHERE r.owner = ?1", Shot.class).setParameter(1, login);
        List<Shot> res = resQuery.getResultList();
        tx1.commit();
        session.close();
        return res;
    }

    public void clearShotsTable(String login) {
        Session session = shotSessionFactoryManager.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("DELETE FROM Shot r WHERE r.owner = ?1").setParameter(1, login).executeUpdate();
        tx1.commit();
        session.close();
    }

    public void addShot(Shot shot) {
        Session session = shotSessionFactoryManager.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(shot);
        tx1.commit();
        session.close();
    }
}
