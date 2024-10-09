package ovchip.dao;

import ovchip.domain.OVChipkaart;
import ovchip.domain.OVChipkaartProduct;
import ovchip.domain.Product;

import java.util.List;

public interface OVChipkaartProductDAO {
    void addProduct(int productNummer, int kaartNummer);
    void deleteProduct(int productNummer, int kaartNummer);
    List<OVChipkaartProduct> getOVChipkaartProducten(int kaartNummer);
    List<Product> getProductenByOVChipkaart(int kaartNummer);
    List<OVChipkaart> getOVChipkaartenByProduct(int productNummer);
}
