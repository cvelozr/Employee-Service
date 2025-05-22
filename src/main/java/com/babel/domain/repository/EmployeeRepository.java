package com.babel.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.babel.domain.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
