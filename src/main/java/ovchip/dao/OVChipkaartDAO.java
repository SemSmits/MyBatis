package ovchip.dao;

import ovchip.domain.OVChipkaart;
import ovchip.domain.Reiziger;

import java.util.List;

public interface OVChipkaartDAO {
    void save(OVChipkaart ovChipkaart);
    void update(OVChipkaart ovChipkaart);
    void delete(OVChipkaart ovChipkaart);
    List<OVChipkaart> findByReiziger(int reizigerId);
}

