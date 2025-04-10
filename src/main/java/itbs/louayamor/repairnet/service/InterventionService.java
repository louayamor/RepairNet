package itbs.louayamor.repairnet.service;

import itbs.louayamor.repairnet.bean.Intervention;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InterventionService {
    Intervention saveIntervention(Intervention intervention);
    Optional<Intervention> getInterventionById(Long id);
    Optional<Intervention> getInterventionByUuid(UUID uuid);
    List<Intervention> getAllInterventions();
    void deleteIntervention(Long id);
    List<Intervention> getInterventionsByTechnicianId(Long technicianId);
    List<Intervention> getInterventionsByEquipmentId(Long equipmentId);
    List<Intervention> getInterventionsByStatus(String status);
    List<Intervention> getInterventionsByDate(LocalDate date);
}
