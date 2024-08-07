package com.math.mathcha.controller;

import com.math.mathcha.dto.request.PaymentDTO;
import com.math.mathcha.dto.request.RechargeRequestDTO;
import com.math.mathcha.dto.response.ResPaymentDTO;
import com.math.mathcha.service.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/payment")
@SecurityRequirement(name = "api")
public class PaymentController {
    @Autowired
     PaymentService  paymentService;
    @PostMapping("/create")
    @PreAuthorize("hasRole('PARENT')")
    public ResponseEntity<String> createPayment(@RequestBody RechargeRequestDTO paymentDTO) throws Exception {
        String savedPayment = paymentService.createUrl(paymentDTO);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }
    @PostMapping("/callback")
    @PreAuthorize("hasRole('PARENT')")
    public ResponseEntity<String> paymentCallback(@RequestBody Map<String, String> queryParams) {
        paymentService.handlePaymentCallback(queryParams);
        return new ResponseEntity<>("Payment processed", HttpStatus.OK);
    }
    @GetMapping("/user/{user_id}")
    @PreAuthorize("hasAnyRole('PARENT','MANAGER')")
    public ResponseEntity <List<PaymentDTO>>getTopicByChapterId (@PathVariable("user_id") int user_id) throws RuntimeException {
        List<PaymentDTO> paymentDTOS = paymentService.getPaymetsByUserId(user_id);
        return ResponseEntity.ok(paymentDTOS);
    }

    @GetMapping("/course/{course_id}")
    @PreAuthorize("hasAnyRole('PARENT','MANAGER')")
    public ResponseEntity <List<ResPaymentDTO>> getPaymetsByCourseId(@PathVariable("course_id") int course_id) throws RuntimeException {
        List<ResPaymentDTO> paymentDTOS = paymentService.getPaymetsByCourseId(course_id);
        return ResponseEntity.ok(paymentDTOS);
    }
    @GetMapping("/user/{user_id}/date/{payment_date}")
    @PreAuthorize("hasAnyRole('PARENT','MANAGER')")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserIdAndPaymentDate(@PathVariable int user_id,
                                                                              @PathVariable String payment_date) {
        List<PaymentDTO> payments = paymentService.getPaymentsByUserIdAndPaymentDate(user_id, payment_date);
        return ResponseEntity.ok(payments);
    }
    @GetMapping("/user/{user_id}/month/{payment_month}")
    @PreAuthorize("hasAnyRole('PARENT','MANAGER')")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserIdAndPaymentMonth(@PathVariable int user_id,
                                                                              @PathVariable String payment_month) {
        List<PaymentDTO> payments = paymentService.getPaymentsByUserIdAndPaymentMonth(user_id, payment_month);
        return ResponseEntity.ok(payments);
    }
    @GetMapping("/user/{user_id}/year/{payment_year}")
    @PreAuthorize("hasAnyRole('PARENT','MANAGER')")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserIdAndPaymentYear(@PathVariable int user_id,
                                                                              @PathVariable String payment_year) {
        List<PaymentDTO> payments = paymentService.getPaymentsByUserIdAndPaymentYear(user_id, payment_year);
        return ResponseEntity.ok(payments);
    }
}
