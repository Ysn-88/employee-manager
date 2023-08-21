package com.yassine.employeemanager.service;

import com.yassine.employeemanager.exception.UseNotFoundException;
import com.yassine.employeemanager.model.Employee;
import com.yassine.employeemanager.repo.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UseNotFoundException("User by id " + id + " was not found"));
    }
    /*public void deleteEmployee(Long id) {

        employeeRepo.deleteEmployeeById(id);
    }*/
    public void deleteEmployee(Long id) {
        // Check if the employee exists before attempting to delete
        if (!employeeRepo.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }

        // Delete the employee
        employeeRepo.deleteById(id);
    }


}
