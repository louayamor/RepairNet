package itbs.louayamor.repairnet.service;

import itbs.louayamor.repairnet.bean.Skill;
import itbs.louayamor.repairnet.bean.Technician;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TechnicianService {
    Technician saveTechnician(Technician technician);
    Optional<Technician> getTechnicianById(Long id);
    List<Technician> getAllTechnicians();
    void deleteTechnician(Long id);
    boolean technicianExists(Long id);
    Technician getTechnicianByUuid(UUID uuid);
    List<Technician> searchTechniciansByName(String name);
    List<Technician> findTechniciansBySkills(List<Skill> skills);
    List<Technician> getAvailableTechnicians();
    Technician getTechnicianByName(String name);
}
