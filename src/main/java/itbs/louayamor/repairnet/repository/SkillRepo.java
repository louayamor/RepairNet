package itbs.louayamor.repairnet.repository;

import itbs.louayamor.repairnet.bean.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillRepo extends JpaRepository<Skill, Long> {
    Skill findByUuid(UUID uuid);
}
