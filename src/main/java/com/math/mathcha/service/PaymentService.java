package com.math.mathcha.service;

import com.math.mathcha.Util.Error.IdInvalidException;
import com.math.mathcha.dto.request.RechargeRequestDTO;
import com.math.mathcha.entity.*;
import com.math.mathcha.mapper.EnrollmentMapper;

import com.math.mathcha.repository.EnrollmentRepository;
import com.math.mathcha.repository.PaymentRepository;
import com.math.mathcha.repository.StudentRepository.StudentRepository;
import com.math.mathcha.repository.UserRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentService {


    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EnrollmentRepository enrollmentRepository;


    public String createUrl(RechargeRequestDTO rechargeRequestDTO) throws NoSuchAlgorithmException, InvalidKeyException, Exception{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);

//        User user = accountUtils.getCurrentUser();
        User user = userRepository.findById(rechargeRequestDTO.getUser_id()).orElse(null);
        Enrollment enrollment = enrollmentRepository.findById(rechargeRequestDTO.getEnrollment_id()).orElse(null);
        String orderId = UUID.randomUUID().toString().substring(0,6);

        Payment payment = new Payment();

        payment.setTotal_money(Double.parseDouble(rechargeRequestDTO.getAmount()));
//        transaction.setTransactionType(TransactionEnum.PENDING);
        payment.setPayment_date(formattedCreateDate);
        payment.setPayment_method("VNPAY");
//        transaction.setDescription("Recharge");
        payment.setUser(user);
        payment.setEnrollment(enrollment);
        Payment transactionReturn = paymentRepository.save(payment);



        String tmnCode = "UEFQX38O";
        String secretKey = "APINBHTVCCO5CGZIN8NL50TQ1OUEJFYO";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String returnUrl = "http://159.223.39.71?student_id="+rechargeRequestDTO.getStudent_id()+"&course_id="+rechargeRequestDTO.getCourse_id();

        String currCode = "VND";
        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef", orderId);
        vnpParams.put("vnp_OrderInfo", "Thanh toan cho ma GD: " + orderId);
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount", rechargeRequestDTO.getAmount());
        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_CreateDate", formattedCreateDate);
        vnpParams.put("vnp_IpAddr", "128.199.178.23");

        StringBuilder signDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            signDataBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("=");
            signDataBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("&");
        }
        signDataBuilder.deleteCharAt(signDataBuilder.length() - 1); // Remove last '&'

        String signData = signDataBuilder.toString();
        String signed = generateHMAC(secretKey, signData);

        vnpParams.put("vnp_SecureHash", signed);

        StringBuilder urlBuilder = new StringBuilder(vnpUrl);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("=");
            urlBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove last '&'

        return urlBuilder.toString();
    }
    private String generateHMAC(String secretKey, String signData) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSha512.init(keySpec);
        byte[] hmacBytes = hmacSha512.doFinal(signData.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : hmacBytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
