package com.math.mathcha.mapper;


import com.math.mathcha.dto.request.PaymentDTO;
import com.math.mathcha.entity.Payment;

public class PaymentMapper {
    public static PaymentDTO mapToPaymentDTO(Payment payment){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPayment_id(payment.getPayment_id());
        paymentDTO.setPayment_date(payment.getPayment_date());
        paymentDTO.setPayment_method(payment.getPayment_method());
        paymentDTO.setTotal_money(payment.getTotal_money());
        paymentDTO.setOrderId(payment.getOrderId());
        paymentDTO.setStudent(payment.getEnrollment().getStudent());
        paymentDTO.setCourse(payment.getEnrollment().getCourse());
        paymentDTO.setUser(payment.getUser());
        return paymentDTO;
    }

    public static Payment mapToPayment(PaymentDTO paymentDTO){
        Payment payment = new Payment();
        payment.setPayment_id(paymentDTO.getPayment_id());
        payment.setTotal_money(paymentDTO.getTotal_money());
        payment.setPayment_method(paymentDTO.getPayment_method());
        payment.setPayment_date(paymentDTO.getPayment_date());
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setUser(paymentDTO.getUser());
        return payment;
    }
}
