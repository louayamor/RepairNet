package itbs.louayamor.repairnet.service.implementation;

import itbs.louayamor.repairnet.bean.Fault;
import itbs.louayamor.repairnet.bean.Equipment;
import itbs.louayamor.repairnet.repository.FaultRepo;
import itbs.louayamor.repairnet.service.FaultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FaultServiceImpl implements FaultService {

	@Autowired
    private FaultRepo faultRepo;

    @Override
    public Fault saveFault(Fault fault) {
        return faultRepo.save(fault);
    }

    @Override
    public Optional<Fault> getFaultById(Long id) {
        return faultRepo.findById(id);
    }

    @Override
    public List<Fault> getAllFaults() {
        return faultRepo.findAll();
    }

    @Override
    public void deleteFault(Long id) {
        faultRepo.deleteById(id);
    }

    @Override
    public boolean faultExists(Long id) {
        return faultRepo.existsById(id);
    }

    @Override
    public Fault getFaultByUuid(UUID uuid) {
        return faultRepo.findByUuid(uuid);
    }

    @Override
    public List<Fault> getFaultsByEquipment(Equipment equipment) {
        return faultRepo.findByEquipment(equipment);
    }

    @Override
    public List<Fault> getAllFaultsSortedByDate() {
        return faultRepo.findAllByOrderByDateReported();
    }

    @Override
    public Page<Fault> getPagedFaults(Pageable pageable) {
        return faultRepo.findAll(pageable);
    }
}
