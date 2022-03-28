package souress.lab3_web.database.eclipselink;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;


public class EclipseSessionFactory {
    private static EntityManagerFactory managerFactory = null;



    private static void initializeSession() {
//TODO remove password
        Map<String, String> preset = new HashMap<>();
        preset.put("javax.persistence.jdbc.user", "name");
        preset.put("javax.persistence.jdbc.password", "password");

        try{
            managerFactory = Persistence.createEntityManagerFactory("Studs", preset);
        } catch (Exception exception){
            System.err.println("Connection failed. Helios settings will be added to preset.");
            try {
                preset.put("javax.persistence.jdbc.url", "jdbc:oracle:thin:@localhost:1521:orbis");
                managerFactory = Persistence.createEntityManagerFactory("Studs", preset);
            } catch (Exception exception1){
                exception1.printStackTrace();
            }
        }
    }

    public static EntityManagerFactory getManagerFactory() {
        if (managerFactory == null){
            initializeSession();
        }
        return managerFactory;
    }
}
