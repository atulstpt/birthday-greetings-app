package com.agstechcloud.birthdayjob.config;

import com.agstechcloud.birthdayjob.job.BirthdayEmailJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail birthdayEmailJobDetail() {
        return JobBuilder.newJob(BirthdayEmailJob.class)
                .withIdentity("birthdayEmailJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger birthdayEmailTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(birthdayEmailJobDetail())
                .withIdentity("birthdayEmailTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?")) // Runs daily at 10 AM IST (12 UTC)
                .build();
    }
}
