package itbs.louayamor.repairnet.service.implementation;

import itbs.louayamor.repairnet.bean.Equipment;
import itbs.louayamor.repairnet.repository.EquipmentRepo;
import itbs.louayamor.repairnet.service.EquipmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepo equipmentRepo;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepo equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepo.findAll();
    }

    @Override
    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepo.findById(id);
    }

    @Override
    public Equipment saveEquipment(Equipment equipment) {
        // Generate UUID if not set
        if (equipment.getUuid() == null) {
            equipment.setUuid(UUID.randomUUID());
        }

        // Save the equipment entity to the database
        return equipmentRepo.save(equipment);
    }

    @Override
    public void deleteEquipment(Long id) {
        equipmentRepo.deleteById(id);
    }

    @Override
    public List<Equipment> searchEquipmentByName(String name) {
        return equipmentRepo.findByNameContainingIgnoreCase(name);
    }
}
