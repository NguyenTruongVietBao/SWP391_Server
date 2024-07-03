package com.math.mathcha.controller;

import com.math.mathcha.dto.request.ChartDTO;
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
    public ResChartDTO getDailyRevenue(@PathVariable String date) {
        return chartService.calculateDailyRevenue(date);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/monthly/{month}")
    public ResChartDTO getMonthlyRevenue(@PathVariable String month) {
        return chartService.calculateMonthlyRevenue(month);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/yearly/{year}")
    public ResChartDTO getYearlyRevenue(@PathVariable String year) {
        return chartService.calculateYearlyRevenue(year);
    }

    @GetMapping("/api/users-purchased/{date}")
    @PreAuthorize("hasRole('MANAGER')")
    public int getUsersPurchasedOnDate(@PathVariable String date) {
        return chartService.countUsersPurchasedOnDate(date);
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