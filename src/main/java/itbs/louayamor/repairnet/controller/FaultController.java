package itbs.louayamor.repairnet.controller;

import itbs.louayamor.repairnet.bean.Fault;
import itbs.louayamor.repairnet.service.FaultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/faults")
public class FaultController {

    private final FaultService faultService;

    public FaultController(FaultService faultService) {
        this.faultService = faultService;
    }

    @GetMapping
    public ResponseEntity<List<Fault>> getAllFaults() {
        List<Fault> faults = faultService.getAllFaults();
        if (faults.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(faults);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fault> getFaultById(@PathVariable Long id) {
        Optional<Fault> fault = faultService.getFaultById(id);
        if (fault.isPresent()) {
            return ResponseEntity.ok(fault.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Fault());  
        }
    }

    @PostMapping
    public ResponseEntity<Fault> createFault(@RequestBody Fault fault) {
        try {
            Fault savedFault = faultService.saveFault(fault);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFault);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Fault());  
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fault> updateFault(@PathVariable Long id, @RequestBody Fault faultDetails) {
        Optional<Fault> existingFault = faultService.getFaultById(id);
        if (existingFault.isPresent()) {
            Fault fault = existingFault.get();
            fault.setDescription(faultDetails.getDescription());
            fault.setCategory(faultDetails.getCategory());
            fault.setEquipment(faultDetails.getEquipment());
            fault.setDateReported(faultDetails.getDateReported());

            Fault updatedFault = faultService.saveFault(fault);
            return ResponseEntity.ok(updatedFault);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Fault());  
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFault(@PathVariable Long id) {
        try {
            faultService.deleteFault(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
    }
}
