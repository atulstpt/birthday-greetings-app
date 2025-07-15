package com.agstechcloud.birthdayjob.controller;

import com.agstechcloud.birthdayjob.entity.Employee;
import com.agstechcloud.birthdayjob.entity.Wish;
import com.agstechcloud.birthdayjob.service.EmailService;
import com.agstechcloud.birthdayjob.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PostMapping("/bulk")
    public List<Employee> createEmployeesBulk(@RequestBody List<Employee> employees) {
        return employeeService.saveEmployeesBulk(employees);
    }

    @GetMapping("/birthdays/today")
    public List<Employee> getTodayBirthdays() {
        return employeeService.findEmployeesWithBirthdayToday();
    }

    @PostMapping("/send-birthday-emails")
    public ResponseEntity<String> sendBirthdayEmails() {
        List<Employee> birthdayEmployees = employeeService.findEmployeesWithBirthdayToday();
        if (birthdayEmployees.isEmpty()) {
            return ResponseEntity.ok("No employees have a birthday today.");
        }
        employeeService.sendBirthdayEmails(birthdayEmployees);
        return ResponseEntity.ok("Birthday emails sent to " + birthdayEmployees.size() + " employees.");
    }

    @PostMapping("/send-wish")
    public ResponseEntity<String> sendWish() {
        List<Employee> birthdayEmployees = employeeService.findEmployeesWithBirthdayToday();
        if (birthdayEmployees.isEmpty()) {
            return ResponseEntity.ok("No employees have a birthday today.");
        }
        Wish wish = new Wish();

        birthdayEmployees.stream().forEach(employee -> {
            employee.setEmail("atulstpt@hotmail.com");
            emailService.sendWishEmail(employee);
        });
        //wish.setEmployee(birthdayEmployees);
       // emailService.sendWishEmail(birthdayEmployees);
        return ResponseEntity.ok("Wish emails sent to " + birthdayEmployees.size() + " employees.");
    }
}
