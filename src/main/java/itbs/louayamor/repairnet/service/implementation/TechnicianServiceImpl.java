package itbs.louayamor.repairnet.service.implementation;

import itbs.louayamor.repairnet.bean.Skill;
import itbs.louayamor.repairnet.bean.Technician;
import itbs.louayamor.repairnet.repository.SkillRepo;
import itbs.louayamor.repairnet.repository.TechnicianRepo;
import itbs.louayamor.repairnet.service.TechnicianService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TechnicianServiceImpl implements TechnicianService {

	@Autowired
    private TechnicianRepo technicianRepo;
	@Autowired
    private SkillRepo skillRepo;

    
    

    @Override
    public Technician saveTechnician(Technician technician) {
        return technicianRepo.save(technician);
    }

    @Override
    public Optional<Technician> getTechnicianById(Long id) {
        return technicianRepo.findById(id);
    }

    @Override
    public List<Technician> getAllTechnicians() {
        return technicianRepo.findAll();
    }

    @Override
    public void deleteTechnician(Long id) {
        technicianRepo.deleteById(id);
    }

    @Override
    public boolean technicianExists(Long id) {
        return technicianRepo.existsById(id);
    }

    @Override
    public Technician getTechnicianByUuid(UUID uuid) {
        return technicianRepo.findByUuid(uuid);
    }

    @Override
    public List<Technician> searchTechniciansByName(String name) {
        return technicianRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Technician> findTechniciansBySkills(List<Skill> skills) {
        return technicianRepo.findBySkillsIn(skills);
    }

    @Override
    public List<Technician> getAvailableTechnicians() {
        return technicianRepo.findByAvailableTrue();
    }

    @Override
    public Technician getTechnicianByName(String name) {
        return technicianRepo.findByName(name);
    }
    
    @Override
    public Technician addSkillToTechnician(Long technicianId, Skill skill) {
        Technician technician = technicianRepo.findById(technicianId)
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        skill.setTechnician(technician); 
        skillRepo.save(skill); 

        technician.getSkills().add(skill); 
        return technicianRepo.save(technician); 
    }
}
