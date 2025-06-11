package itbs.louayamor.repairnet.bean;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "technician")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "technician", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills;

    @Column(nullable = false)
    private Boolean available; 
    
    @Column(name = "image_url", length = 512)  
    private String imageUrl;
}
