package ovchip.dao;

import org.apache.ibatis.annotations.Param;
import ovchip.domain.OVChipkaart;
import ovchip.domain.OVChipkaartProduct;
import ovchip.domain.Product;

import java.util.List;

public interface OVChipkaartProductDAO {
    void addProduct(@Param("productNummer") int productNummer, @Param("kaartNummer") int kaartNummer);
    void deleteProduct(@Param("productNummer") int productNummer, @Param("kaartNummer") int kaartNummer);
    List<OVChipkaartProduct> getOVChipkaartProducten();
    List<Product> getProductenByOVChipkaart(int kaartNummer);
    List<OVChipkaart> getOVChipkaartenByProduct(int productNummer);
}
