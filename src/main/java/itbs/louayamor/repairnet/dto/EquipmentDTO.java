package itbs.louayamor.repairnet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentDTO {
    private Long id;
    private UUID uuid;
    private String name;
    private String status;
    private LocalDate acquisitionDate;
    
}
