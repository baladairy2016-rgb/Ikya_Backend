package com.vidhuras.hospital_app.Repository;

import com.vidhuras.hospital_app.Entity.Doctor;
import com.vidhuras.hospital_app.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Long> {
}
