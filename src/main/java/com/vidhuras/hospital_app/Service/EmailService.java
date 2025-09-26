package com.vidhuras.hospital_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("IKYA Hospital - Your OTP for Appointment Confirmation");
        message.setText("Dear Patient,\n\nYour OTP for confirming the appointment is: "
                + otp + "\n\nThank you,\nIKYA Hospital");
        mailSender.send(message);
        System.out.println("OTP Email sent successfully to " + toEmail);

    }

    public void sendAppointmentConfirmation(String patientEmail, String patientName,
                                            String doctorName, String date, String time) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(patientEmail);
        message.setSubject("IKYA Hospital - Appointment Confirmation");
        message.setText("Dear " + patientName + ",\n\n" +
                "Your appointment has been successfully booked.\n\n" +
                "Doctor: " + doctorName + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n\n" +
                "Thank you for choosing IKYA Hospital.");
        mailSender.send(message);
        System.out.println("Appointment confirmation email sent to " + patientEmail);
    }

    // Send notification to admin
    public void sendAdminNotification(String adminEmail, String patientName,
                                      String doctorName, String date, String time,
                                      String patientEmail, String patientPhone) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("IKYA Hospital - New Appointment Booked");
        message.setText("Patient " + patientName + " has booked an online appointment.\n\n" +
                "Doctor: " + doctorName + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n" +
                "Patient Email: " + patientEmail + "\n" +
                "Patient Phone: " + patientPhone);
        mailSender.send(message);
        System.out.println("Admin notification email sent to " + adminEmail);
    }
}
