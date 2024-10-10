package ovchip.main;

import ovchip.dao.OVChipkaartDAO;
import ovchip.dao.ReizigerDAO;
import ovchip.domain.OVChipkaart;
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
                OVChipkaartDAO ovchipkaartDAO = session.getMapper(OVChipkaartDAO.class);

                // Voeg een nieuwe reiziger toe
                Reiziger nieuweReiziger = new Reiziger();
                nieuweReiziger.setId(52);
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

                // Reiziger ophalen met id
                reizigerDAO.findById(nieuweReiziger.getId());
                session.commit();
                System.out.println("Reiziger met id: " + nieuweReiziger.getId() + "\n" + nieuweReiziger);

                // Reiziger ophalen met geboortedatum
                LocalDate geboortedatum = LocalDate.of(1990, 1, 1);
                reizigerDAO.findByGbdatum(java.sql.Date.valueOf(geboortedatum));
                session.commit();
                System.out.println("Reiziger met de geboortedatum: " + geboortedatum + "\n" + nieuweReiziger);

                // Voeg een nieuwe OV-chipkaart toe aan deze reiziger
                OVChipkaart ovChipkaart = new OVChipkaart();
                ovChipkaart.setKaartNummer(12347);
                ovChipkaart.setGeldigTot(LocalDate.of(2025, 12, 31));
                ovChipkaart.setKlasse(2);
                ovChipkaart.setSaldo(50.0);
                ovChipkaart.setReizigerId(nieuweReiziger.getId());
                ovchipkaartDAO.save(ovChipkaart);
                session.commit();
                System.out.println("OVChipkaart toegevoegd: " + ovChipkaart);

                // Haal alle OV-chipkaarten op van de reiziger
                List<OVChipkaart> ovChipkaarten = ovchipkaartDAO.findByReiziger(nieuweReiziger.getId());
                System.out.println("OVChipkaarten van reiziger:");
                for (OVChipkaart kaart : ovChipkaarten) {
                    System.out.println(kaart);
                }

                // Verwijder eerst de gerelateerde OV-chipkaarten
                for (OVChipkaart kaart : ovChipkaarten) {
                    ovchipkaartDAO.delete(kaart);
                }
                session.commit();
                System.out.println("Alle OVChipkaarten van reiziger verwijderd.");

                // Verwijder de aangemaakte reiziger
                reizigerDAO.delete(nieuweReiziger.getId());
                session.commit();
                System.out.println("Reiziger verwijderd: " + nieuweReiziger);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
