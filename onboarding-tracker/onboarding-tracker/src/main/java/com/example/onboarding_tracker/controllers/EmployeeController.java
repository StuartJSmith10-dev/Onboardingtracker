package com.example.onboarding_tracker.controllers;

import com.example.onboarding_tracker.jpa_objects.Employee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.example.onboarding_tracker.repositories.EmployeeRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
	@RequestMapping("/api/employees")
	public class EmployeeController {

		private final EmployeeRepository repository;

        @Autowired
		public EmployeeController(EmployeeRepository repository) {
			this.repository = repository;
		}

		// 5.2 API 1: Capture an employee
		@PostMapping
		public ResponseEntity<Employee> captureEmployee(@Valid @RequestBody Employee employee) {
			Employee savedEmployee = repository.save(employee);
			return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
		}

		// API 2: Fetch all captured employees
		@GetMapping
		public List<Employee> getAllEmployees() {
			return repository.findAll();
		}
        
        // API 3: Update an employee
        @PutMapping ("/{id}") 
        public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
            Employee existingEmployee = repository.findById(id).orElse(null);
            if (existingEmployee == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            employee.setId(id);
            Employee updatedEmployee = repository.save(employee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        }

        // API 4: Delete an employee
        @DeleteMapping ("/{id}")
        public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
            Employee existingEmployee = repository.findById(id).orElse(null);
            if (existingEmployee == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            repository.delete(existingEmployee);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

	}
