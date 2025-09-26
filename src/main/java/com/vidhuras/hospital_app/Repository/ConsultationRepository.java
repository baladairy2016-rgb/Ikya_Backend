package com.vidhuras.hospital_app.Repository;

import com.vidhuras.hospital_app.Entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
