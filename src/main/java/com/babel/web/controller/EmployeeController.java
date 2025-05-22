package com.babel.web.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babel.application.service.EmployeeService;
import com.babel.domain.dto.EmployeeDTO;
import com.babel.domain.model.Employee;
import com.babel.exception.EmployeeNotFoundException;
import com.babel.exception.ExceptionGenericException;
import com.babel.web.wapper.EmployeeMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Employees resources")
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Operation(summary = "This Service get all Employees.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDTO>> getEmployees() throws EmployeeNotFoundException {
		log.info("Fetching all employees");
		List<Employee> employees = employeeService.getEmployees();
		if (employees!=null && !employees.isEmpty()) {
			List<EmployeeDTO> employeesDTO = employees.stream().map(EmployeeMapper::toDTO).toList();
			return ResponseEntity.ok(employeesDTO);
		}
		log.info("Employees not found");
		throw new EmployeeNotFoundException("The employees not found.");
	}

	@Operation(summary = "This Service delete one Employee.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployeee(@PathVariable Long id) throws EmployeeNotFoundException {
		log.info("delete employee id");
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		if (employee.isEmpty()) {
			log.debug("Employee with id {} not found",id);
			throw new EmployeeNotFoundException("The employee by ID " + id + " not found.");
		}
		employeeService.deleteEmployee(id);
		log.info("Delete completed");
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "This Service update one Employee, no need all resource.")
	@PatchMapping("/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Validated(EmployeeDTO.OnUpdate.class) @RequestBody EmployeeDTO employeeDTO) throws EmployeeNotFoundException {
		log.info("update employee start");
		Optional<Employee> employeeExist = employeeService.getEmployeeById(id);
		if (employeeExist.isEmpty()) {
			log.debug("Employee with id {} not found",id);
			throw new EmployeeNotFoundException("The employee by ID " + id + " not found.");
		}
		Employee employeeUpdated= EmployeeMapper.validateUpdate(employeeExist.get(), employeeDTO); 
		employeeService.updateEmployee(employeeUpdated);
		return ResponseEntity.ok(EmployeeMapper.toDTO(employeeUpdated));
	}

	@Operation(summary = "This Service add one Employee at least.")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addEmployees(@Validated(EmployeeDTO.OnCreate.class) @RequestBody List<EmployeeDTO> employeeDTO) {
		log.info("Add employees");
		if (employeeDTO.isEmpty()) {
			log.info("Add one at least.");
			throw new ExceptionGenericException("You must add one employee at least.");
		}
		List<Employee> empleados = employeeDTO.stream().map(EmployeeMapper::toEntity).toList();
		employeeService.addEmployees(empleados);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
