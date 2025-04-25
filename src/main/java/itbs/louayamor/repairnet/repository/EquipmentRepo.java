package itbs.louayamor.repairnet.repository;

import itbs.louayamor.repairnet.bean.Equipment;
import itbs.louayamor.repairnet.enumerator.EquipmentStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipmentRepo extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findById(Long id);

    //Main CRUD
    
    List<Equipment> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);

    //Custom Methods

    Equipment findByUuid(UUID uuid);

    List<Equipment> findByStatus(EquipmentStatus status);

    List<Equipment> findByNameContainingIgnoreCase(String name);

    List<Equipment> findByStatusOrderByAcquisitionDateAsc(EquipmentStatus status);

    Page<Equipment> findAll(Pageable pageable);

    List<Equipment> findByNameContainingIgnoreCaseOrStatus(String name, EquipmentStatus status);

    void deleteByStatus(EquipmentStatus status);
}
