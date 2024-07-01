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
    @PostMapping("/revenue")
    public ResChartDTO getRevenue(@RequestParam int month, @RequestParam int year, @RequestParam int interval) {
        return chartService.calculateRevenue(month, year, interval);
    }
}
