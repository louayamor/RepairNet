package itbs.louayamor.repairnet.repository;

import itbs.louayamor.repairnet.bean.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InterventionRepo extends JpaRepository<Intervention, Long> {
    List<Intervention> findByTechnicianId(Long technicianId);
    List<Intervention> findByEquipmentId(Long equipmentId);
    Optional<Intervention> findByUuid(UUID uuid);
    List<Intervention> findByStatus(String status);
    List<Intervention> findByDate(LocalDate date);
}
