package ovchip.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ovchip.domain.Product;

import java.util.List;

public class ProductDAOMyBatis implements ProductDAO {
    private SqlSessionFactory sqlSessionFactory;

    public ProductDAOMyBatis(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void save(Product product) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("ovchip.dao.productDAO.save", product);
            session.commit();
        }
    }

    @Override
    public void update(Product product) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update("ovchip.dao.productDAO.update", product);
            session.commit();
        }
    }

    @Override
    public void delete(Product product) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("ovchip.dao.productDAO.delete", product);
            session.commit();
        }
    }

    @Override
    public Product findById(int nummer) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("ovchip.dao.productDAO.findById", nummer);
        }
    }

    @Override
    public List<Product> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("ovchip.dao.productDAO.findAll");
        }
    }
}
