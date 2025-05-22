package com.babel.application.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babel.application.service.EmployeeService;
import com.babel.domain.model.Employee;
import com.babel.domain.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void addEmployees(List<Employee> employees) {
		employees.forEach(employee -> employeeRepository.save(employee));
	}

}
