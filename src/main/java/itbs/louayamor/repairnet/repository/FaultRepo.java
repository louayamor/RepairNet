package itbs.louayamor.repairnet.repository;

import itbs.louayamor.repairnet.bean.Fault;
import itbs.louayamor.repairnet.bean.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FaultRepo extends JpaRepository<Fault, Long> {

    Fault save(Fault fault);

    Optional<Fault> findById(Long id);

    List<Fault> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);

    Fault findByUuid(UUID uuid);

    List<Fault> findByEquipment(Equipment equipment);

    List<Fault> findAllByOrderByDateReported();

    Page<Fault> findAll(Pageable pageable);
}
