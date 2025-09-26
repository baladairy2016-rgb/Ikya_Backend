package com.vidhuras.hospital_app.Service.Impl;

import com.vidhuras.hospital_app.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class EmailTestApp implements CommandLineRunner {

    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(EmailTestApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        emailService.sendOtpEmail("your-email@gmail.com", "123456");
        System.out.println("Email sent!");
    }
}
