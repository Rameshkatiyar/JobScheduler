package com.tech.scheduler;

import com.tech.jobs.ReminderJob;
import com.tech.model.ReminderJobRequest;
import com.tech.model.ReminderJobResponse;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class ReminderScheduler {

    @Autowired
    private Scheduler scheduler;

    public ReminderJobResponse scheduleReminderJob(ReminderJobRequest reminderJobRequest) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.of(reminderJobRequest.getDateTime(),
                                                      reminderJobRequest.getTimeZone());
            if(dateTime.isBefore(ZonedDateTime.now())) {
                return ReminderJobResponse.builder()
                        .message("Reminder dateTime should be after current time!")
                        .success(false)
                        .build();
            }

            JobDetail jobDetail = buildJobDetail(reminderJobRequest);
            Trigger trigger = buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);

            return ReminderJobResponse.builder()
                    .message("Reminder Scheduled Successfully! JobId: " + jobDetail.getKey().getName())
                    .success(true)
                    .build();
        } catch (SchedulerException e) {
            log.error("Error in scheduling", e.getMessage());
            return ReminderJobResponse.builder()
                    .message(e.getMessage())
                    .success(false)
                    .build();
        }
    }

    private JobDetail buildJobDetail(ReminderJobRequest reminderJobRequest) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("message", reminderJobRequest.getMessage());

        return JobBuilder.newJob(ReminderJob.class)
                .withIdentity(UUID.randomUUID().toString(), "reminder-jobs")
                .withDescription("Send Reminder Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "reminder-triggers")
                .withDescription("Send Reminder Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
