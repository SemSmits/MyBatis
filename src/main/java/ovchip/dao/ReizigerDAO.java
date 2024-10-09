package ovchip.dao;

import ovchip.domain.Reiziger;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ReizigerDAO {
    List<Reiziger> findAll();
    void save(Reiziger reiziger);
    void update(Reiziger reiziger);
    void delete(int reizigerId);
    Reiziger findById(int reizigerId);
    List<Reiziger> findByGbdatum(Date date);
}