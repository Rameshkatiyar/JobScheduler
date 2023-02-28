package com.tech.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReminderJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        log.info("Running Job: {}", context.getJobDetail().getKey().getName());
        //TODO: Write code below to send the reminder msg or email.
    }
}
