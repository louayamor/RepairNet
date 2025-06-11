package itbs.louayamor.repairnet.bean;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

import itbs.louayamor.repairnet.enumerator.EquipmentStatus;

@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(nullable = false)
    @NotNull(message = "Name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Status is required")
    private EquipmentStatus status;

    private LocalDate acquisitionDate;
    
    @Column(name = "image_url", length = 512)  
    private String imageUrl;
    
}
