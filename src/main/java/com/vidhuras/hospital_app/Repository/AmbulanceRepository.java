package com.vidhuras.hospital_app.Repository;

import com.vidhuras.hospital_app.Entity.Ambulance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmbulanceRepository  extends JpaRepository<Ambulance, Long> {
}
