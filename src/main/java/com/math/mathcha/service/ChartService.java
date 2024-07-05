package com.math.mathcha.service;

import com.math.mathcha.dto.response.ResChartDTO;
import com.math.mathcha.entity.*;
import com.math.mathcha.repository.CourseRepository.CourseRepository;
import com.math.mathcha.repository.EnrollmentRepository;
import com.math.mathcha.repository.PaymentRepository;
import com.math.mathcha.repository.StudentRepository.StudentRepository;
import com.math.mathcha.repository.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public ResChartDTO calculateRevenue(String startDate, String endDate) {
        List<Payment> payments = paymentRepository.findPaymentsBetweenDates(startDate, endDate);
        double totalRevenue = payments.stream().mapToDouble(Payment::getTotal_money).sum();
        List<String> labels = payments.stream()
                .map(Payment::getPayment_date)
                .collect(Collectors.toList());
        List<Double> revenues = payments.stream()
                .map(Payment::getTotal_money)
                .collect(Collectors.toList());

        List<User> users = userRepository.findUsersByPaymentDates(startDate, endDate);
        List<Course> courses = courseRepository.findCoursesByPaymentDates(startDate, endDate);

        ResChartDTO resChartDTO = new ResChartDTO();
        resChartDTO.setLabels(labels);
        resChartDTO.setRevenue(revenues);
        resChartDTO.setTotalRevenue(totalRevenue);
        resChartDTO.setUsers(users);
        resChartDTO.setCourses(courses);
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

    public Double calculateTotalRevenueByCourseId(int courseId) {
        return paymentRepository.findTotalRevenueByCourseId(courseId);
    }

    public Double calculateTotalSpentByUserId(int userId) {
        return paymentRepository.findTotalSpentByUserId(userId);
    }

    public int countTotalUsersPurchasedCourseOnDate(int courseId, String date) {
        String startDate = date + "000000"; // Start of the day
        String endDate = date + "235959";   // End of the day
        return paymentRepository.countTotalUsersPurchasedCourseOnDate(courseId, startDate, endDate);
    }
//    public int countUsersPurchasedOnDate(String date) {
//        String startDate = date + "000000"; // Start of the day
//        String endDate = date + "235959";   // End of the day
//        return paymentRepository.countDistinctUsersByPaymentDate(startDate, endDate);
//    }
}
