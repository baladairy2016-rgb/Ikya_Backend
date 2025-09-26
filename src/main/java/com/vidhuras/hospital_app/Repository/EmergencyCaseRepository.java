package com.vidhuras.hospital_app.Repository;

import com.vidhuras.hospital_app.Entity.EmergencyCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyCaseRepository extends JpaRepository<EmergencyCase, Long> {
}
