package ovchip.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ovchip.domain.OVChipkaart;
import ovchip.domain.Reiziger;

import java.util.List;

public class OVChipkaartDAOMyBatis implements OVChipkaartDAO {
    private SqlSessionFactory sqlSessionFactory;

    public OVChipkaartDAOMyBatis(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void save(OVChipkaart ovChipkaart) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("ovchip.dao.AdresDAO.save", ovChipkaart);
            session.commit();
        }
    }

    @Override
    public void update(OVChipkaart ovChipkaart) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update("ovchip.dao.AdresDAO.update", ovChipkaart);
            session.commit();
        }
    }

    @Override
    public void delete(OVChipkaart ovChipkaart) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("ovchip.dao.AdresDAO.delete", ovChipkaart);
            session.commit();
        }
    }

    @Override
    public List<OVChipkaart> findByReiziger(int reizigerId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("ovchip.dao.OVChipkaartDAO.findByReiziger", reizigerId);
        }
    }

}
