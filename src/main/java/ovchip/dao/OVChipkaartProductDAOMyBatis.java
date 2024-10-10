package ovchip.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ovchip.domain.*;

import java.util.List;
import java.util.Map;

public class OVChipkaartProductDAOMyBatis implements OVChipkaartProductDAO {
    private SqlSessionFactory sqlSessionFactory;

    public OVChipkaartProductDAOMyBatis(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void addProduct(int productNummer, int kaartNummer) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("ovchip.dao.OVChipkaartProductDAO.addProduct", Map.of("kaartNummer", kaartNummer, "productNummer", productNummer));
            session.commit();
        }
    }

    @Override
    public void deleteProduct(int productNummer, int kaartNummer){
        try (SqlSession session = sqlSessionFactory.openSession()) {
            OVChipkaartProduct productCard = new OVChipkaartProduct(productNummer, kaartNummer);
            session.delete("ovchip.dao.OVChipkaartProductDAO.deleteProduct", productCard);
            session.commit();
        }
    }

    @Override
    public List<OVChipkaartProduct> getOVChipkaartProducten() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("ovchip.dao.OVChipkaartProductDAO.getOVChipkaartProducten");
        }
    }


    @Override
    public List<Product> getProductenByOVChipkaart(int kaartNummer) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("ovchip.dao.OVChipkaartProductDAO.getProductenByOVChipkaart", kaartNummer);
        }
    }

    @Override
    public List<OVChipkaart> getOVChipkaartenByProduct(int productNummer){
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("ovchip.dao.OVChipkaartProductDAO.getOVChipkaartenByProduct", productNummer);
        }
    }
}
