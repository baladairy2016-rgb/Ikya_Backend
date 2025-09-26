package com.vidhuras.hospital_app.Repository;

import com.vidhuras.hospital_app.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {}