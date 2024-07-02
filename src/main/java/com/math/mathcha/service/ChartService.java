package com.math.mathcha.service;

import com.math.mathcha.dto.response.ResChartDTO;
import com.math.mathcha.entity.Payment;
import com.math.mathcha.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {

    @Autowired
    private PaymentRepository paymentRepository;

    public ResChartDTO calculateRevenue(String startDate, String endDate) {
        List<Payment> payments = paymentRepository.findPaymentsBetweenDates(startDate, endDate);
        double totalRevenue = payments.stream().mapToDouble(Payment::getTotal_money).sum();

        List<String> labels = payments.stream()
                .map(Payment::getPayment_date)
                .collect(Collectors.toList());

        List<Double> revenues = payments.stream()
                .map(Payment::getTotal_money)
                .collect(Collectors.toList());

        ResChartDTO resChartDTO = new ResChartDTO();
        resChartDTO.setLabels(labels);
        resChartDTO.setRevenue(revenues);
        resChartDTO.setTotalRevenue(totalRevenue);

        return resChartDTO;
    }

    public ResChartDTO calculateDailyRevenue(String date) {
        return calculateRevenue(date + "000000", date + "235959");
    }

    public ResChartDTO calculateMonthlyRevenue(String month) {
        String startDate = month + "01000000";
        String endDate = month + "31" + "235959";
        return calculateRevenue(startDate, endDate);
    }

    public ResChartDTO calculateYearlyRevenue(String year) {
        String startDate = year + "0101000000";
        String endDate = year + "1231235959";
        return calculateRevenue(startDate, endDate);
    }
}
