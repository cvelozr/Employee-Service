package com.babel.application.service;

import java.util.List;
import java.util.Optional;

import com.babel.domain.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getEmployees();
	
	public Optional<Employee> getEmployeeById(Long id);
	
	public void deleteEmployee(Long id);
	
	public void updateEmployee(Employee employee);
	
	public void addEmployees(List<Employee> employees);

}
