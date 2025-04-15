package itbs.louayamor.repairnet.service.implementation;

import itbs.louayamor.repairnet.bean.Intervention;
import itbs.louayamor.repairnet.repository.InterventionRepo;
import itbs.louayamor.repairnet.service.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InterventionServiceImpl implements InterventionService {

	@Autowired
    private InterventionRepo interventionRepo;

    @Override
    public Intervention saveIntervention(Intervention intervention) {
        return interventionRepo.save(intervention);
    }

    @Override
    public Optional<Intervention> getInterventionById(Long id) {
        return interventionRepo.findById(id);
    }

    @Override
    public Optional<Intervention> getInterventionByUuid(UUID uuid) {
        return interventionRepo.findByUuid(uuid);
    }

    @Override
    public List<Intervention> getAllInterventions() {
        return interventionRepo.findAll();
    }

    @Override
    public void deleteIntervention(Long id) {
        interventionRepo.deleteById(id);
    }

    @Override
    public List<Intervention> getInterventionsByTechnicianId(Long technicianId) {
        return interventionRepo.findByTechnicianId(technicianId);
    }

    @Override
    public List<Intervention> getInterventionsByEquipmentId(Long equipmentId) {
        return interventionRepo.findByEquipmentId(equipmentId);
    }

    @Override
    public List<Intervention> getInterventionsByStatus(String status) {
        return interventionRepo.findByStatus(status);
    }

    @Override
    public List<Intervention> getInterventionsByDate(LocalDate date) {
        return interventionRepo.findByDate(date);
    }
}
