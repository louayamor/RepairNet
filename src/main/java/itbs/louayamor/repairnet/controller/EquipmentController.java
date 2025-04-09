package itbs.louayamor.repairnet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import itbs.louayamor.repairnet.bean.Equipment;
import itbs.louayamor.repairnet.service.EquipmentService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

	private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);
    private final EquipmentService equipmentService;
    
    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        List<Equipment> equipmentList = equipmentService.getAllEquipment();
        return ResponseEntity.ok(equipmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        Optional<Equipment> equipment = equipmentService.getEquipmentById(id);
        return equipment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Equipment> saveEquipment(@Valid @RequestBody Equipment equipment){
    	
        Equipment savedEquipment = equipmentService.saveEquipment(equipment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Equipment>> searchEquipment(@RequestParam String name) {
        List<Equipment> equipmentList = equipmentService.searchEquipmentByName(name);
        return ResponseEntity.ok(equipmentList);
    }
}
