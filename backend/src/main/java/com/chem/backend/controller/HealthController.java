package com.chem.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HealthController {

    @GetMapping("/api/health")
    public String health() {
        return "Backend running";
    }
}
