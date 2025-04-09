package itbs.louayamor.repairnet.bean;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "faults")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid = UUID.randomUUID();  

    @Column(nullable = false)
    private String description;  

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FaultCategory category;  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;  

    @Column(name = "report_date", nullable = false)
    private LocalDateTime dateReported;  
}
