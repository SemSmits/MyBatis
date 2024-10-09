package ovchip.dao;

import ovchip.domain.*;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    void save(Product product);
    void update(Product product);
    void delete(Product product);
    Product findById(int nummer);
    List<Product> findAll();
}
