package com.math.mathcha.controller;

import com.math.mathcha.dto.request.RechargeRequestDTO;
import com.math.mathcha.service.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/payment")
@SecurityRequirement(name = "api")
public class PaymentController {
    @Autowired
     PaymentService  paymentService;
    @PostMapping("/create")
//    @PreAuthorize("hasRole('PARENT')")
    public ResponseEntity<String> createPayment(@RequestBody RechargeRequestDTO paymentDTO) throws Exception {
        String savedPayment = paymentService.createUrl(paymentDTO);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }
}
