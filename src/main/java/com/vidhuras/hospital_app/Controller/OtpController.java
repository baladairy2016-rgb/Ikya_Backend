package com.vidhuras.hospital_app.Controller;

import com.vidhuras.hospital_app.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/otp")
@CrossOrigin("*")
public class OtpController {

    @Autowired
    private EmailService emailService;

    private Map<String, String> otpStore = new HashMap<>(); // key: email, value: otp

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStore.put(email, otp);
        emailService.sendOtpEmail(email, otp);
        return ResponseEntity.ok("OTP sent to " + email);
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean valid = otpStore.getOrDefault(email, "").equals(otp);
        if(valid) otpStore.remove(email);
        return ResponseEntity.ok(valid);


    }


}

