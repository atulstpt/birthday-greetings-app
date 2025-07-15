package com.agstechcloud.birthdayjob.service;

import com.agstechcloud.birthdayjob.entity.Employee;
import com.agstechcloud.birthdayjob.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailService emailService;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public List<Employee> saveEmployeesBulk(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    public List<Employee> findEmployeesWithBirthdayToday() {
        return employeeRepository.findEmployeesWithBirthdayToday();
    }

    public void sendBirthdayEmails(List<Employee> employees) {
        for (Employee employee : employees) {
            String subject = "Happy Birthday, " + employee.getFirstName() + "!";
            String message = "Dear " + employee.getFirstName() + ",\n\n" +
                    "Wishing you a very Happy Birthday! We hope you have a fantastic day filled with joy and celebration.\n\n" +
                    "Best wishes,\nThe Company Team";
            emailService.sendEmail(employee.getEmail(), subject, message);
        }
    }



}
