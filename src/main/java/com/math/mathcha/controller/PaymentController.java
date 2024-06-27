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
    @PreAuthorize("hasRole('PARENT')")
    public ResponseEntity getTopicByChapterId (@PathVariable("user_id") int user_id) throws RuntimeException {
        List<PaymentDTO> paymentDTOS = paymentService.getPaymetsByUserId(user_id);
        return ResponseEntity.ok(paymentDTOS);
    }
    @GetMapping("/date/{date}")
    @PreAuthorize("hasRole('PARENT')")
    public ResponseEntity<List<ResPaymentDTO>> getPaymentsByDate(@PathVariable("date") String date) {
        LocalDate localDate = LocalDate.parse(date); // Parse date from string yyyy-MM-dd
        List<ResPaymentDTO> paymentDTOs = paymentService.getPaymentsByDate(localDate);
        return ResponseEntity.ok(paymentDTOs);
    }

}
