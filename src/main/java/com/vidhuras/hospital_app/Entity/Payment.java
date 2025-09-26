package com.vidhuras.hospital_app.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
    private int amount; // in paise
    private String currency;
    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}


