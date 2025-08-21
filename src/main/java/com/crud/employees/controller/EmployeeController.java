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
    public List<Employee> searchEmployees(@RequestParam String employee) {
        return employeeService.findByDepartment(employee);
    }
//                .stream()
//                .filter(employee -> employee.getName().toLowerCase().contains(name.toLowerCase()))
//                .toList();






    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }
    @PostMapping("/create")
    public Employee createEmployee(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String department,
            @RequestParam String position,
            @RequestParam Double salary) {

        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setSalary(salary);

        return employeeService.createEmployee(employee);
    }
//    @PostMapping("/create")
//    public Employee createEmployee(
//            @RequestParam String name,
//            @RequestParam String email,
//            @RequestParam String department,
//            @RequestParam String position,
//            @RequestParam Double salary) {
//        return employeeService.createEmployee(name, email, department, position, salary);
//    }





    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,@RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }
    @PutMapping("/update")
    public Employee updateEmployeeByParams(@RequestParam Long id,
                                           @RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam String department,
                                           @RequestParam String position,
                                           @RequestParam Double salary) {
        return employeeService.updateEmployeeByParams(id, name, email, department, position, salary);
    }





    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}