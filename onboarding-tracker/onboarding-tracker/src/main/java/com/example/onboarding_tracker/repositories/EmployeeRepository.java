package com.example.onboarding_tracker.repositories;

import com.example.onboarding_tracker.jpa_objects.Employee;
import org.springframework.data.jpa.repository.JpaRepository; // or CrudRepository
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Your repository methods go here
}
