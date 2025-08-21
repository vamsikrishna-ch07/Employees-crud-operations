package com.crud.employees.controller;

import com.crud.employees.entity.Employee;
import com.crud.employees.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
//@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();

    }
   @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {

    return employeeService.getEmployeeById(id);
    }
    @GetMapping ("/search")
    public List<Employee> searchEmployees(@RequestParam String name) {
        return employeeService.findByDepartment(name);
    }
//                .stream()
//                .filter(employee -> employee.getName().toLowerCase().contains(name.toLowerCase()))
//                .toList();

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping
    public Employee updateEmployee(Long id, Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}