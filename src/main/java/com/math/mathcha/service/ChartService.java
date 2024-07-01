package com.math.mathcha.service;

import com.math.mathcha.dto.response.ResChartDTO;
import com.math.mathcha.entity.Payment;
import com.math.mathcha.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {

    @Autowired
    private PaymentRepository paymentRepository;

    public ResChartDTO calculateRevenue(int month, int year, int interval) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        cal.set(Calendar.MONTH, month - 1); // Tháng trong Calendar bắt đầu từ 0
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = cal.getTime();
        String startDateString = sdf.format(startDate);

        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date endDate = cal.getTime();
        String endDateString = sdf.format(endDate);

        List<Payment> payments = paymentRepository.findPaymentsBetweenDates(startDateString, endDateString);

        double totalRevenue = payments.stream().mapToDouble(Payment::getTotal_money).sum();

        List<String> labels = payments.stream()
                .map(Payment::getPayment_date) // payment_date đã là String
                .collect(Collectors.toList());

        List<Double> revenues = payments.stream()
                .map(Payment::getTotal_money)
                .collect(Collectors.toList());

        ResChartDTO resChartDTO = new ResChartDTO();
        resChartDTO.setLabels(labels);
        resChartDTO.setRevenue(revenues);
        resChartDTO.setTotalRevenue(totalRevenue);

        // Tính toán doanh thu hàng ngày, hàng tháng, hàng năm
        resChartDTO.setDailyRevenue(totalRevenue / cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        resChartDTO.setMonthlyRevenue(totalRevenue);
        resChartDTO.setYearlyRevenue(totalRevenue * 12);

        return resChartDTO;
    }
}
