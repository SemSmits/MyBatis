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
            session.insert("ovchip.dao.OVChipkaartDAO.save", ovChipkaart);
            session.commit();
        }
    }

    @Override
    public void update(OVChipkaart ovChipkaart) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update("ovchip.dao.OVChipkaartDAO.update", ovChipkaart);
            session.commit();
        }
    }

    @Override
    public void delete(OVChipkaart ovChipkaart) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("ovchip.dao.OVChipkaartDAO.delete", ovChipkaart);
            session.commit();
        }
    }

    @Override
    public List<OVChipkaart> findByReiziger(int reizigerId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("ovchip.dao.OVChipkaartDAO.findByReiziger", reizigerId);
        }
    }

    @Override
    public List<OVChipkaart> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("ovchip.dao.OVChipkaartDAO.findAll");
        }
    }

    @Override
    public OVChipkaart findById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("ovchip.dao.OVChipkaartDAO.find", id);
        }
    }

}
