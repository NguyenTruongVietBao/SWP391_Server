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

import java.util.List;


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
    public ResponseEntity<ResChartDTO> getDailyRevenue(@RequestBody String date) {
        ResChartDTO resChartDTO = chartService.calculateDailyRevenue(date);
        return ResponseEntity.ok(resChartDTO);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/monthly")
    public ResponseEntity<ResChartDTO> getMonthlyRevenue(@RequestBody String month) {
        ResChartDTO resChartDTO = chartService.calculateMonthlyRevenue(month);
        return ResponseEntity.ok(resChartDTO);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/revenue/yearly")
    public ResponseEntity<ResChartDTO> getYearlyRevenue(@RequestBody String year) {
        ResChartDTO resChartDTO = chartService.calculateYearlyRevenue(year);
        return ResponseEntity.ok(resChartDTO);
    }

    @GetMapping("/api/users-purchased")
    @PreAuthorize("hasRole('MANAGER')")
    public int getUsersPurchasedOnDate(@RequestParam String date) {
        return chartService.countUsersPurchasedOnDate(date);
    }
}