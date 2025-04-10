package itbs.louayamor.repairnet.controller;

import itbs.louayamor.repairnet.bean.Skill;
import itbs.louayamor.repairnet.bean.Technician;
import itbs.louayamor.repairnet.service.TechnicianService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/technicians")
public class TechnicianController {

    private static final Logger logger = LoggerFactory.getLogger(TechnicianController.class);
    private final TechnicianService technicianService;

    @Autowired
    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping
    public ResponseEntity<List<Technician>> getAllTechnicians() {
        List<Technician> technicians = technicianService.getAllTechnicians();
        return ResponseEntity.ok(technicians);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTechnicianById(@PathVariable Long id) {
        Optional<Technician> technicianOpt = technicianService.getTechnicianById(id);
        if (technicianOpt.isPresent()) {
            return ResponseEntity.ok(technicianOpt.get());
        } else {
            logger.warn("Technician not found with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Technician not found.");
        }
    }


    @PostMapping
    public ResponseEntity<?> saveTechnician(@Valid @RequestBody Technician technician) {
        try {
            Technician saved = technicianService.saveTechnician(technician);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            logger.error("Error saving technician: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not save technician.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTechnician(@PathVariable Long id) {
        if (!technicianService.technicianExists(id)) {
            logger.warn("Attempted to delete non-existent technician with id {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Technician not found.");
        }
        try {
            technicianService.deleteTechnician(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error deleting technician with id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete technician.");
        }
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> getTechnicianByUuid(@PathVariable UUID uuid) {
        try {
            Technician technician = technicianService.getTechnicianByUuid(uuid);
            return technician != null
                    ? ResponseEntity.ok(technician)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Technician not found.");
        } catch (Exception e) {
            logger.error("Error retrieving technician by UUID {}: {}", uuid, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving technician.");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Technician>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(technicianService.searchTechniciansByName(name));
    }

    @PostMapping("/skills")
    public ResponseEntity<List<Technician>> findBySkills(@RequestBody List<Skill> skills) {
        return ResponseEntity.ok(technicianService.findTechniciansBySkills(skills));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Technician>> getAvailableTechnicians() {
        return ResponseEntity.ok(technicianService.getAvailableTechnicians());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getTechnicianByName(@PathVariable String name) {
        Technician tech = technicianService.getTechnicianByName(name);
        return tech != null
                ? ResponseEntity.ok(tech)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Technician not found.");
    }
}
