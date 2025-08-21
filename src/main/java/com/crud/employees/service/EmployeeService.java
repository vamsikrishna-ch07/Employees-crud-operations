package com.crud.employees.service;

import com.crud.employees.entity.Employee;
import com.crud.employees.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {

        return employeeRepository.findById(id);
    }
     public List<Employee> findByDepartment(String name) {
        return employeeRepository.findByDepartment(name);
    }




    public Employee createEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }
//    public Employee createEmployee(String name, String email, String department, String position, Double salary) {
//        Employee employee = new Employee();
//        employee.setName(name);
//        employee.setEmail(email);
//        employee.setDepartment(department);
//        employee.setPosition(position);
//        employee.setSalary(salary);
//        return employeeRepository.save(employee);
//    }



    public Employee updateEmployee(Long id, Employee employee) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setName(employee.getName());
                    existingEmployee.setDepartment(employee.getDepartment());
                    existingEmployee.setSalary(employee.getSalary());
                    // Assuming Employee has a setName method
                    return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }
    public Employee updateEmployeeByParams(Long id, String name, String email, String department, String position, Double salary) {
        return null;
    }



    public Employee updateEmployee(Long id, Map<String, Object> updates) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    if (updates.containsKey("name")) {
                        existingEmployee.setName((String) updates.get("name"));
                    }
                    if (updates.containsKey("email")) {
                        existingEmployee.setEmail((String) updates.get("email"));
                    }
                    if (updates.containsKey("department")) {
                        existingEmployee.setDepartment((String) updates.get("department"));
                    }
                    if (updates.containsKey("position")) {
                        existingEmployee.setPosition((String) updates.get("position"));
                    }
                    if (updates.containsKey("salary")) {
                        existingEmployee.setSalary((Double) updates.get("salary"));
                        // cast carefully, if you are sending Integer you may need Number conversion
                    }
                    return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public void deleteEmployee(Long id) {

        employeeRepository.deleteById(id);
    }


    }

