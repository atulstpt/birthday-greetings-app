package com.agstechcloud.birthdayjob.repository;

import com.agstechcloud.birthdayjob.entity.Employee;
import com.agstechcloud.birthdayjob.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE MONTH(e.dateOfBirth) = MONTH(CURRENT_DATE) AND DAY(e.dateOfBirth) = DAY(CURRENT_DATE)")
    List<Employee> findEmployeesWithBirthdayToday();
}

