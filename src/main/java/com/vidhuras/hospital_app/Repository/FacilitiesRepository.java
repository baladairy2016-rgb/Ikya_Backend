package com.vidhuras.hospital_app.Repository;

import com.vidhuras.hospital_app.Entity.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FacilitiesRepository extends JpaRepository<Facilities, Long> {
    List<Facilities> findByStatus(String status);
}
