package com.vidhuras.hospital_app.Repository;

import com.vidhuras.hospital_app.Entity.Slider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SliderRepository extends JpaRepository<Slider, Long> {
    List<Slider> findByStatus(String status);
}