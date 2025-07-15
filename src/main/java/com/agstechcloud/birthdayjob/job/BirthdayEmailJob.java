package com.agstechcloud.birthdayjob.job;

import com.agstechcloud.birthdayjob.service.EmailService;
import com.agstechcloud.birthdayjob.service.EmployeeService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BirthdayEmailJob implements Job {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmailService emailService;

    @Override
    public void execute(JobExecutionContext context) {
        employeeService.findEmployeesWithBirthdayToday()
                .forEach(emailService::sendBirthdayEmail);
    }
}
