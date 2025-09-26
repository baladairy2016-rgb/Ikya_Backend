package com.vidhuras.hospital_app.Repository;

import com.vidhuras.hospital_app.Entity.Specialities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialitiesRepository extends JpaRepository<Specialities, Long> {
    List<Specialities> findByStatus(String status);
}
