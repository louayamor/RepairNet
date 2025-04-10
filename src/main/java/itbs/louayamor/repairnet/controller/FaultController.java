package itbs.louayamor.repairnet.controller;

import itbs.louayamor.repairnet.bean.Fault;
import itbs.louayamor.repairnet.bean.Equipment;
import itbs.louayamor.repairnet.service.FaultService;
import itbs.louayamor.repairnet.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/faults")
public class FaultController {

    private final FaultService faultService;
    private final EquipmentService equipmentService;

    @Autowired
    public FaultController(FaultService faultService, EquipmentService equipmentService) {
        this.faultService = faultService;
        this.equipmentService = equipmentService;
    }

    @PostMapping
    public Fault createFault(@RequestBody Fault fault) {
        return faultService.saveFault(fault);
    }

    @GetMapping
    public List<Fault> getAllFaults() {
        return faultService.getAllFaults();
    }

    @GetMapping("/{id}")
    public Fault getFaultById(@PathVariable Long id) {
        return faultService.getFaultById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteFault(@PathVariable Long id) {
        faultService.deleteFault(id);
    }

    @GetMapping("/uuid/{uuid}")
    public Fault getFaultByUuid(@PathVariable UUID uuid) {
        return faultService.getFaultByUuid(uuid);
    }

    @GetMapping("/equipment/{equipmentId}")
    public List<Fault> getFaultsByEquipment(@PathVariable Long equipmentId) {
        Optional<Equipment> equipment = equipmentService.getEquipmentById(equipmentId);
        return equipment.map(faultService::getFaultsByEquipment).orElse(null);
    }

    @GetMapping("/sorted")
    public List<Fault> getFaultsSortedByDate() {
        return faultService.getAllFaultsSortedByDate();
    }

    @GetMapping("/page")
    public Page<Fault> getPagedFaults(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        return faultService.getPagedFaults(PageRequest.of(page, size));
    }
}
