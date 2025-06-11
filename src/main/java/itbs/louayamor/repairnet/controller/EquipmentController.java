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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/equipment")
public class EquipmentController {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Equipment equipment = equipmentService.getEquipmentById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

            // Save file to disk (you can customize the path)
            String uploadDir = "uploads/equipment-images/";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // Make sure the directory exists
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) uploadFolder.mkdirs();

            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Save the file path or URL in Equipment
            equipment.setImageUrl("/" + uploadDir + fileName);
            equipmentService.saveEquipment(equipment);

            return ResponseEntity.ok("Image uploaded successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }
    
    // Get all equipment
    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        try {
            List<Equipment> equipmentList = equipmentService.getAllEquipment();
            if (equipmentList.isEmpty()) {
                return ResponseEntity.noContent().build(); 
            }
            return ResponseEntity.ok(equipmentList);
        } catch (Exception e) {
            logger.error("Error fetching equipment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        try {
            Optional<Equipment> equipment = equipmentService.getEquipmentById(id);
            return equipment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404 Not Found
        } catch (Exception e) {
            logger.error("Error fetching equipment by ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
        }
    }

    @PostMapping
    public ResponseEntity<Equipment> saveEquipment(@Valid @RequestBody Equipment equipment) {
        try {
            Equipment savedEquipment = equipmentService.saveEquipment(equipment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipment); 
        } catch (Exception e) {
            logger.error("Error saving equipment", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        try {
            if (!equipmentService.getEquipmentById(id).isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
            }
            equipmentService.deleteEquipment(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } catch (Exception e) {
            logger.error("Error deleting equipment with ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Equipment>> searchEquipment(@RequestParam String name) {
        try {
            List<Equipment> equipmentList = equipmentService.searchEquipmentByName(name);
            if (equipmentList.isEmpty()) {
                return ResponseEntity.noContent().build(); 
            }
            return ResponseEntity.ok(equipmentList);
        } catch (Exception e) {
            logger.error("Error searching equipment by name: " + name, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
        }
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> handleValidationExceptions(BindException ex) {
        StringBuilder errorMessages = new StringBuilder();
        for (FieldError fieldError : ex.getFieldErrors()) {
            errorMessages.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("\n");
        }
        logger.error("Validation error occurred: {}", errorMessages.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages.toString()); 
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        logger.error("An error occurred", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage()); 
    }
}
