package com.math.mathcha.service;

import com.math.mathcha.dto.request.PaymentDTO;
import com.math.mathcha.dto.request.RechargeRequestDTO;
import com.math.mathcha.dto.request.UserDTO;
import com.math.mathcha.dto.response.ResCreateUserDTO;
import com.math.mathcha.dto.response.ResPaymentDTO;
import com.math.mathcha.entity.*;

import com.math.mathcha.mapper.PaymentMapper;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.repository.EnrollmentRepository;
import com.math.mathcha.repository.PaymentRepository;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentService {


    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    CourseRepository   courseRepository;

    public String createUrl(RechargeRequestDTO rechargeRequestDTO) throws NoSuchAlgorithmException, InvalidKeyException, Exception{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);

//        User user = accountUtils.getCurrentUser();
        String orderId = UUID.randomUUID().toString().substring(0,6);




        String tmnCode = "UEFQX38O";
        String secretKey = "APINBHTVCCO5CGZIN8NL50TQ1OUEJFYO";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String returnUrl = "http://mathcha.online?student_id=" + rechargeRequestDTO.getStudent_id() + "&course_id=" + rechargeRequestDTO.getCourse_id();
//        String returnUrl = "http://159.223.39.71?student_id=" + rechargeRequestDTO.getStudent_id() + "&course_id=" + rechargeRequestDTO.getCourse_id();
//        String returnUrl = "http://localhost:3000?student_id=" + rechargeRequestDTO.getStudent_id() + "&course_id=" + rechargeRequestDTO.getCourse_id();
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
        vnpParams.put("vnp_Amount", rechargeRequestDTO.getAmount()+"00");
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
    public void handlePaymentCallback(Map<String, String> queryParams) {
        if (queryParams.containsKey("student_id")) {
            queryParams.put("student_id", queryParams.get("student_id"));
        }
        if (queryParams.containsKey("course_id")) {
            queryParams.put("course_id", queryParams.get("course_id"));
        }
        RechargeRequestDTO rechargeRequestDTO = new RechargeRequestDTO();
        rechargeRequestDTO.setUser_id(Integer.parseInt(queryParams.get("user_id")));
        rechargeRequestDTO.setEnrollment_id(Integer.parseInt(queryParams.get("enrollment_id")));
        rechargeRequestDTO.setAmount(queryParams.get("amount"));

        // Thêm các tham số student_id và course_id nếu có
        if (queryParams.containsKey("student_id")) {
            rechargeRequestDTO.setStudent_id(Integer.parseInt(queryParams.get("student_id")));
        }
        if (queryParams.containsKey("course_id")) {
            rechargeRequestDTO.setCourse_id(Integer.parseInt(queryParams.get("course_id")));
        }

        // Lưu thông tin thanh toán
        savePayment(rechargeRequestDTO);
    }
    public void savePayment(RechargeRequestDTO rechargeRequestDTO) {
        User user = userRepository.findById(rechargeRequestDTO.getUser_id()).orElse(null);
        Enrollment enrollment = enrollmentRepository.findById(rechargeRequestDTO.getEnrollment_id()).orElse(null);
        if (user != null && enrollment != null) {
            Payment payment = new Payment();
            payment.setTotal_money(Double.parseDouble(rechargeRequestDTO.getAmount())/100);
            payment.setPayment_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            payment.setPayment_method("VNPAY");
            payment.setUser(user);
            payment.setEnrollment(enrollment);
            paymentRepository.save(payment);
        }
    }
    public List<PaymentDTO> getPaymetsByUserId(int user_id) throws RuntimeException {
        User user = userRepository.findById(user_id)
                .orElseThrow(() ->  new RuntimeException("User ID is invalid"));
        List<Payment> payments = paymentRepository.findPaymentByUserId(user_id);
        return payments.stream()
                .map(PaymentMapper::mapToPaymentDTO)
                .collect(Collectors.toList());
    }
//    public List<ResPaymentDTO> getPaymentsByDate(LocalDate date) {
//        String formattedDate = date.toString(); // yyyy-MM-dd
//        List<Payment> payments = paymentRepository.findPaymentsByDate(formattedDate);
//        return payments.stream()
//                .map(PaymentMapper::mapToPaymentDTO)
//                .map(this::convertToResPaymentDTO)
//                .collect(Collectors.toList());
//    }
    public ResPaymentDTO convertToResPaymentDTO(PaymentDTO paymentDTO) {
        ResPaymentDTO res = new ResPaymentDTO();
        res.setPayment_date(paymentDTO.getPayment_date());
        res.setPayment_id(paymentDTO.getPayment_id());
        res.setTotal_money(paymentDTO.getTotal_money());
        res.setPayment_method(paymentDTO.getPayment_method());
        return res;
    }
    public List<ResPaymentDTO> getPaymetsByCourseId(int course_id) throws RuntimeException {
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() ->  new RuntimeException("Course ID is invalid"));
        List<Payment> payments = paymentRepository.findPaymentByCourseId(course_id);
        return payments.stream()
                .map(PaymentMapper::mapToPaymentDTO)
                .map(this::convertToResPaymentDTO)
                .collect(Collectors.toList());
    }
}
