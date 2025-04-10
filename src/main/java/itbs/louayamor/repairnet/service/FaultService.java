package itbs.louayamor.repairnet.service;

import itbs.louayamor.repairnet.bean.Fault;
import itbs.louayamor.repairnet.bean.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FaultService {
    Fault saveFault(Fault fault);
    Optional<Fault> getFaultById(Long id);
    List<Fault> getAllFaults();
    void deleteFault(Long id);
    boolean faultExists(Long id);
    Fault getFaultByUuid(UUID uuid);
    List<Fault> getFaultsByEquipment(Equipment equipment);
    List<Fault> getAllFaultsSortedByDate();
    Page<Fault> getPagedFaults(Pageable pageable);
}
