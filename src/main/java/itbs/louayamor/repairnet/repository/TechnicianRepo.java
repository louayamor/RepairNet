package itbs.louayamor.repairnet.repository;

import itbs.louayamor.repairnet.bean.Technician;
import itbs.louayamor.repairnet.bean.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TechnicianRepo extends JpaRepository<Technician, Long> {
    Optional<Technician> findById(Long id);
    List<Technician> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Technician findByUuid(UUID uuid);
    List<Technician> findByNameContainingIgnoreCase(String name);
    List<Technician> findBySkillsIn(List<Skill> skills);
    List<Technician> findByAvailableTrue();
    Technician findByName(String name);
}
