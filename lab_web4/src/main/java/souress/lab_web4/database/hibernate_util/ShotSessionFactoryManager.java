package souress.lab_web4.database.hibernate_util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import souress.lab_web4.entities.Shot;

import javax.ejb.Stateful;

@Stateful
public class ShotSessionFactoryManager {

    private SessionFactory sf;

    public SessionFactory getSessionFactory() {
        if (sf == null) {
            Configuration configuration = new Configuration().addAnnotatedClass(Shot.class).configure();
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sf = configuration.buildSessionFactory(sr);
        }
        return sf;
    }
}
