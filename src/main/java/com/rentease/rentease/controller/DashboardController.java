package com.rentease.rentease.controller;

import com.rentease.rentease.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/owner/{ownerId}")
    public Object getOwnerDashboard(@PathVariable Long ownerId) {
        return dashboardService.getOwnerDashboard(ownerId);
    }

    @GetMapping("/tenant/{tenantId}")
    public Object getTenantDashboard(@PathVariable Long tenantId) {
        return dashboardService.getTenantDashboard(tenantId);
    }
}