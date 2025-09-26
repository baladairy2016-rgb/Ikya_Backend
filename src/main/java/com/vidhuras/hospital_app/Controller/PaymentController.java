package com.vidhuras.hospital_app.Controller;

import com.razorpay.Utils;
import com.vidhuras.hospital_app.Entity.Appointment;
import com.vidhuras.hospital_app.Entity.Doctor;
import com.vidhuras.hospital_app.Entity.Payment;
import com.vidhuras.hospital_app.Repository.AppointmentRepository;
import com.vidhuras.hospital_app.Repository.DoctorSetupRepository;
import com.vidhuras.hospital_app.Repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.vidhuras.hospital_app.Service.EmailService;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Value("${razorpay.key_id}")
    private String razorpayKeyId;

    @Value("${razorpay.key_secret}")
    private String razorpaySecret;

    private final AppointmentRepository appointmentRepository;
    private final PaymentRepository paymentRepository;
    private final DoctorSetupRepository doctorRepository;
    private EmailService emailService;

    public PaymentController(AppointmentRepository appointmentRepository,
                             PaymentRepository paymentRepository,
                             DoctorSetupRepository doctorRepository,
                             EmailService emailService) {
        this.appointmentRepository = appointmentRepository;
        this.paymentRepository = paymentRepository;
        this.doctorRepository = doctorRepository;
        this.emailService = emailService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> data) {
        try {
            Long appointmentId = Long.valueOf(data.get("appointmentId").toString());
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new RuntimeException("Appointment not found"));

            Doctor doctor = doctorRepository.findById(appointment.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));

            int amount = doctor.getConsultingFee() != null ?
                    Integer.parseInt(doctor.getConsultingFee()) : 0;

            if (amount <= 0) throw new RuntimeException("Invalid fee for appointment.");

            appointment.setFee(amount);
            appointmentRepository.save(appointment);

            RazorpayClient client = new RazorpayClient(razorpayKeyId, razorpaySecret);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount * 100); // in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("payment_capture", 1);

            Order order = client.orders.create(orderRequest);

            Map<String, Object> response = new HashMap<>();
            response.put("orderId", order.get("id"));
            response.put("key", razorpayKeyId);
            response.put("amount", order.get("amount"));
            response.put("currency", order.get("currency"));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating order: " + e.getMessage());
        }
    }

    @PostMapping("/verify")
    @Transactional
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> data) {
        try {
            String razorpayOrderId = data.get("razorpay_order_id");
            String razorpayPaymentId = data.get("razorpay_payment_id");
            String razorpaySignature = data.get("razorpay_signature");
            Long appointmentId = Long.valueOf(data.get("appointmentId"));

            Map<String, String> params = new HashMap<>();
            params.put("razorpay_order_id", razorpayOrderId);
            params.put("razorpay_payment_id", razorpayPaymentId);
            params.put("razorpay_signature", razorpaySignature);

            boolean isValid = Utils.verifyPaymentSignature(new JSONObject(params), razorpaySecret);
            if (!isValid) return ResponseEntity.badRequest().body("Invalid payment signature!");

            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new RuntimeException("Appointment not found"));

            Payment payment = new Payment();
            payment.setAppointment(appointment);
            payment.setRazorpayOrderId(razorpayOrderId);
            payment.setRazorpayPaymentId(razorpayPaymentId);
            payment.setRazorpaySignature(razorpaySignature);
            payment.setAmount(appointment.getFee() * 100); // in paise
            payment.setCurrency("INR");
            payment.setPaymentDate(LocalDateTime.now());

            paymentRepository.save(payment);

            appointment.setPaymentStatus("PAID");
            appointmentRepository.save(appointment);

            // Fetch doctor info
            Doctor doctor = doctorRepository.findById(appointment.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));

            String patientEmail = appointment.getEmail();
            String patientName = appointment.getPatientName();
            String doctorName = doctor.getDoctorName();
            String date = appointment.getAppointmentDate().toString();
            String time = appointment.getAppointmentTime();
            String patientPhone = appointment.getPhoneNumber();
            String adminEmail = "suprajaajjada55@gmail.com";

            try {
                emailService.sendAppointmentConfirmation(patientEmail, patientName, doctorName, date, time);
                emailService.sendAdminNotification(adminEmail, patientName, doctorName, date, time, patientEmail, patientPhone);
            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("Error sending emails: " + e.getMessage());
            }

            return ResponseEntity.ok("Payment verified & emails sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Payment verification failed: " + e.getMessage());
        }
    }
}
