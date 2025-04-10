package itbs.louayamor.repairnet.dto;

import itbs.louayamor.repairnet.enumerator.FaultCategory;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FaultDTO {

    private Long id;
    private UUID uuid;
    private String description;
    private FaultCategory category;
    private Long equipmentId;
    private LocalDateTime dateReported;
}
