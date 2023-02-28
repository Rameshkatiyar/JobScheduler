package com.tech.controller;

import com.tech.model.ReminderJobRequest;
import com.tech.model.ReminderJobResponse;
import com.tech.scheduler.ReminderScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
public class JobSchedulerController {

    @Autowired
    private ReminderScheduler reminderScheduler;

    @GetMapping(value = "/get-job-list")
    public ResponseEntity<?> getJobList() {
        return ResponseEntity.ok("Working....!" + ZonedDateTime.now());
    }

    @PostMapping(value = "/reminder/schedule")
    public ResponseEntity<ReminderJobResponse> scheduleReminder(@RequestBody
                                                                ReminderJobRequest reminderJobRequest) {
        ReminderJobResponse reminderJobResponse = reminderScheduler.scheduleReminderJob(
                reminderJobRequest);
        return ResponseEntity.ok(reminderJobResponse);
    }
}
