package ovchip.main;

import ovchip.dao.OVChipkaartDAO;
import ovchip.dao.OVChipkaartProductDAO;
import ovchip.dao.ProductDAO;
import ovchip.dao.ReizigerDAO;
import ovchip.domain.OVChipkaart;
import ovchip.domain.OVChipkaartProduct;
import ovchip.domain.Product;
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
                ProductDAO productDAO = session.getMapper(ProductDAO.class);
                OVChipkaartProductDAO ovchipkaartProductDAO = session.getMapper(OVChipkaartProductDAO.class);

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

                // Update de OV-Chipkaart
                ovChipkaart.setGeldigTot(LocalDate.of(2026, 12, 31));
                ovChipkaart.setKlasse(3);
                ovchipkaartDAO.update(ovChipkaart);
                session.commit();
                System.out.println("OVchipkaart bijgewerkt: " + ovChipkaart);

                // Alle OV-Chipkaarten ophalen
                List<OVChipkaart> ovchipkaarten = ovchipkaartDAO.findAll();
                System.out.println("Alle ovchipkaarten:");
                for (OVChipkaart ovchipkaart : ovchipkaarten) {
                    System.out.println(ovchipkaart);
                }


                // Haal alle OV-chipkaarten op van de reiziger
                List<OVChipkaart> ovChipkaarten = ovchipkaartDAO.findByReiziger(nieuweReiziger.getId());
                System.out.println("OVChipkaarten van reiziger:");
                for (OVChipkaart kaart : ovChipkaarten) {
                    System.out.println(kaart);
                }

                // Haal OV-chipkaart op met id
                ovchipkaartDAO.findById(ovChipkaart.getKaartNummer());
                session.commit();
                System.out.println("OV-chipkaart met id: " + ovChipkaart.getKaartNummer() + "\n" + ovChipkaart);

                // Voeg nieuw product toe
                Product nieuwProduct = new Product();
                nieuwProduct.setProductNummer(10);
                nieuwProduct.setNaam("test product");
                nieuwProduct.setBeschrijving("beschrijving test product");
                nieuwProduct.setPrijs(10);
                productDAO.save(nieuwProduct);
                session.commit();
                System.out.println("Product toegevoegd: " + nieuwProduct);

                // Alle producten ophalen
                List<Product> producten = productDAO.findAll();
                System.out.println("Alle producten:");
                for (Product product : producten) {
                    System.out.println(product);
                }

                // Update product
                nieuwProduct.setPrijs(15);
                productDAO.update(nieuwProduct);
                session.commit();
                System.out.println("Product bijgewerkt: " + nieuwProduct);

                // product ophalen van id
                productDAO.findById(nieuwProduct.getProductNummer());
                session.commit();
                System.out.println("Product met id: " + nieuwProduct.getProductNummer() + "\n" + nieuwProduct);

                // Koppel OV-chipkaart aan product
                ovchipkaartProductDAO.addProduct(nieuwProduct.getProductNummer(), ovChipkaart.getKaartNummer());
                session.commit();
                System.out.println("product (" + nieuwProduct + ") gekoppeld aan ovChipkaart: " + ovChipkaart);

                // Haal alle producten op van een OV-Chipkaart
                List<Product> productenVanOVChipkaart = ovchipkaartProductDAO.getProductenByOVChipkaart(ovChipkaart.getKaartNummer());
                System.out.println("Alle producten van ovchipkaart: ");
                for (Product product : productenVanOVChipkaart) {
                    System.out.println(product);
                }

                // Haal alle ov-chipkaarten op die bij een product horen
                List<OVChipkaart> ovChipkaartenVanProduct = ovchipkaartProductDAO.getOVChipkaartenByProduct(nieuwProduct.getProductNummer());
                System.out.println("Alle ovchipkaarten van product: ");
                for (OVChipkaart ovchipkaarts : ovChipkaartenVanProduct) {
                    System.out.println(ovchipkaarts);
                }

                // Haal alle producten en ov-chipkaarten op
                List<OVChipkaartProduct> productenEnChipkaarten = ovchipkaartProductDAO.getOVChipkaartProducten();
                System.out.println("Alle ovchipkaarten met gelinkte producten: ");
                for (OVChipkaartProduct ovchipkaartenProducten : productenEnChipkaarten) {
                    System.out.println(ovchipkaartenProducten);
                }

                // Verwijder producten bij ov-chipkaart
                ovchipkaartProductDAO.deleteProduct(nieuwProduct.getProductNummer(), ovChipkaart.getKaartNummer());
                session.commit();
                System.out.println("producten verwijderd van ovchipkaart: " + ovChipkaart);

                // Verwijder product
                productDAO.delete(nieuwProduct);
                session.commit();
                System.out.println("product verwijderd: " + nieuwProduct);

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
