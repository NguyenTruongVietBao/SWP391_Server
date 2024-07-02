package com.math.mathcha.controller;

import com.math.mathcha.dto.request.ChartDTO;
import com.math.mathcha.dto.response.ResChartDTO;
import com.math.mathcha.service.ChartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/revenue/daily")
    public ResChartDTO getDailyRevenue(@RequestParam String date) {
        return chartService.calculateDailyRevenue(date);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/monthly")
    public ResChartDTO getMonthlyRevenue(@RequestParam String month) {
        return chartService.calculateMonthlyRevenue(month);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/yearly")
    public ResChartDTO getYearlyRevenue(@RequestParam String year) {
        return chartService.calculateYearlyRevenue(year);
    }
}