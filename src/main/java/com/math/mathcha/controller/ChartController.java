package com.math.mathcha.controller;

import com.math.mathcha.dto.response.ResChartDTO;

import com.math.mathcha.service.ChartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/chart")
@SecurityRequirement(name = "api")

public class ChartController {
    @Autowired
    private ChartService chartService;

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/daily/{date}")
    public ResponseEntity<ResChartDTO> getDailyRevenue(@PathVariable String date) {
        ResChartDTO revenueData = chartService.calculateDailyRevenue(date);
        return ResponseEntity.ok(revenueData);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/monthly/{month}")
    public ResponseEntity<ResChartDTO> getMonthlyRevenue(@PathVariable String month) {
        ResChartDTO revenueData = chartService.calculateMonthlyRevenue(month);
        return ResponseEntity.ok(revenueData);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/yearly/{year}")
    public ResponseEntity<ResChartDTO> getYearlyRevenue(@PathVariable String year) {
        ResChartDTO revenueData = chartService.calculateYearlyRevenue(year);
        return ResponseEntity.ok(revenueData);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/from/{startDate}/to/{endDate}")
    public ResponseEntity<ResChartDTO> getRevenueBetweenDates(@PathVariable String startDate, @PathVariable String endDate) {
        ResChartDTO revenueData = chartService.calculateRevenue(startDate + "000000", endDate + "235959");
        return ResponseEntity.ok(revenueData);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/api/total-users-purchased-course/{courseId}/{date}")
    public int getTotalUsersPurchasedCourseOnDate(@PathVariable int courseId, @PathVariable String date) {
        return chartService.countTotalUsersPurchasedCourseOnDate(courseId, date);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/course/{courseId}")
    public ResponseEntity<Double> getTotalRevenueByCourseId(@PathVariable int courseId) {
        Double totalRevenue = chartService.calculateTotalRevenueByCourseId(courseId);
        return ResponseEntity.ok(totalRevenue);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/user/{userId}")
    public ResponseEntity<Double> getTotalSpentByUserId(@PathVariable int userId) {
        Double totalSpent = chartService.calculateTotalSpentByUserId(userId);
        return ResponseEntity.ok(totalSpent);
    }
}