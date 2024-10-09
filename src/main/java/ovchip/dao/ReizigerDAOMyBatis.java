package ovchip.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ovchip.domain.Reiziger;

import java.util.List;

public class ReizigerDAOMyBatis implements ReizigerDAO {
    private SqlSessionFactory sqlSessionFactory;

    public ReizigerDAOMyBatis(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<Reiziger> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("ovchip.dao.ReizigerDAO.findAll");
        }
    }

    @Override
    public void save(Reiziger reiziger) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("ovchip.dao.ReizigerDAO.save", reiziger);
            session.commit();
        }
    }

    @Override
    public void update(Reiziger reiziger) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update("ovchip.dao.ReizigerDAO.update", reiziger);
            session.commit();
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("ovchip.dao.ReizigerDAO.delete", id);
            session.commit();
        }
    }
}