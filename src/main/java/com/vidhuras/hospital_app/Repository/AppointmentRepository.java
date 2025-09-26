package com.vidhuras.hospital_app.Repository;

import com.vidhuras.hospital_app.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
