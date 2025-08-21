package com.crud.employees.repository;

import com.crud.employees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
   List<Employee> findByDepartment(String name);
    // Additional query methods can be defined here if needed
}
