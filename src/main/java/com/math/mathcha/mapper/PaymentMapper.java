//package com.math.mathcha.mapper;
//
//
//import com.math.mathcha.dto.request.RechargeRequestDTO;
//import com.math.mathcha.entity.Payment;
//
//public class PaymentMapper {
//    public static RechargeRequestDTO mapToPaymentDTO(Payment payment){
//        RechargeRequestDTO paymentDTO = new RechargeRequestDTO();
//        paymentDTO.setUser_id(payment.getPayment_id());
//        paymentDTO.setEnrollment_id(payment.getTotal_money());
//        paymentDTO.setStudent_id(payment.getPayment_method());
//        paymentDTO.setCourse_id(payment.());
//        paymentDTO.setAmount(paymentDTO.getAmount());
//        return paymentDTO;
//    }
//
//    public static Payment mapToPayment(RechargeRequestDTO paymentDTO){
//        Payment payment = new Payment();
//        payment.setPayment_id(paymentDTO.getPayment_id());
//        payment.setTotal_money(paymentDTO.getTotal_money());
//        payment.setPayment_method(paymentDTO.getPayment_method());
//        payment.setPayment_date(paymentDTO.getPayment_date());
//        return payment;
//    }
//}
