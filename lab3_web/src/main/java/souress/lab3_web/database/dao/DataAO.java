package souress.lab3_web.database.dao;


import lombok.Data;
import souress.lab3_web.database.eclipselink.EclipseSessionFactory;
import souress.lab3_web.util.Shots;

import javax.persistence.*;
import java.util.List;

@Data
public class DataAO {

    private EntityManagerFactory managerFactory;

    public DataAO(){
        managerFactory = EclipseSessionFactory.getManagerFactory();
    }

    public List<Shots> getShots(String id){
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Shots> result = null;
        try {
            transaction.begin();
            TypedQuery<Shots> query = (TypedQuery<Shots>) entityManager.createNativeQuery("SELECT * FROM SHOTS WHERE SESSIONID=?", Shots.class);
            query.setParameter(1, id);
            result = query.getResultList();
            transaction.commit();
        } catch (Exception exception){
            try {
                System.err.println("Error in get shots");
                exception.printStackTrace();
                transaction.rollback();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        entityManager.close();
        return result;
    }

    public void clearTable(String id){
        System.out.println("Clearing start");
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Query query = entityManager.createNativeQuery("DELETE FROM SHOTS WHERE SESSIONID=?");
            query.setParameter(1, id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception exception){
            try {
                System.err.println("Error in clear table.");
                exception.printStackTrace();
                transaction.rollback();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        entityManager.close();
    }

    public void addShot(Shots shot){
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.persist(shot);
            transaction.commit();
        } catch(Exception exception){
            try {
                System.err.println("Error in add shot.");
                exception.printStackTrace();
                transaction.rollback();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
