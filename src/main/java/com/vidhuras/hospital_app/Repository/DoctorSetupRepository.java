package com.vidhuras.hospital_app.Repository;

import com.vidhuras.hospital_app.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorSetupRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByStatusIgnoreCase(String status);

}
