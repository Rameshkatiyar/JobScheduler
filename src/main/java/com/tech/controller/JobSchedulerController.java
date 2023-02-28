package com.tech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobSchedulerController {

    @GetMapping(value = "/get-job-list")
    public ResponseEntity<?> getJobList() {
        return ResponseEntity.ok("Working....!");
    }
}
