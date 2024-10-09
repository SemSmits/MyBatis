package ovchip.main;

import ovchip.dao.ReizigerDAO;
import ovchip.domain.Reiziger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String resources = "mybatis-config.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resources)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            try (SqlSession session = sqlSessionFactory.openSession()) {
                // Krijg de ReizigerMapper
                ReizigerDAO reizigerDAO = session.getMapper(ReizigerDAO.class);

                // Voeg een nieuwe reiziger toe
                Reiziger nieuweReiziger = new Reiziger();
                nieuweReiziger.setId(600);
                nieuweReiziger.setVoorletters("J");
                nieuweReiziger.setTussenvoegsel("van");
                nieuweReiziger.setAchternaam("Dijk");
                nieuweReiziger.setGeboortedatum(LocalDate.of(1990, 1, 1));
                reizigerDAO.save(nieuweReiziger);
                session.commit();
                System.out.println("Reiziger toegevoegd: " + nieuweReiziger);

                // Alle reizigers ophalen
                List<Reiziger> reizigers = reizigerDAO.findAll();
                System.out.println("Alle reizigers:");
                for (Reiziger reiziger : reizigers) {
                    System.out.println(reiziger);
                }

                // Reiziger updaten
                nieuweReiziger.setAchternaam("Vermeer");
                nieuweReiziger.setVoorletters("P");
                reizigerDAO.update(nieuweReiziger);
                session.commit();
                System.out.println("Reiziger bijgewerkt: " + nieuweReiziger);

                // Reiziger verwijderen
                reizigerDAO.delete(nieuweReiziger.getId());
                session.commit();
                System.out.println("Reiziger verwijderd: " + nieuweReiziger.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
