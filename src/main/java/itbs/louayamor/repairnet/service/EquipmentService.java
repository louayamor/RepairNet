package itbs.louayamor.repairnet.service;

import itbs.louayamor.repairnet.bean.Equipment;
import java.util.List;
import java.util.Optional;

public interface EquipmentService {
    List<Equipment> getAllEquipment();
    Optional<Equipment> getEquipmentById(Long id);
    Equipment saveEquipment(Equipment equipment);
    void deleteEquipment(Long id);
    List<Equipment> searchEquipmentByName(String name);
}
