package com.babel.web.wapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.babel.domain.dto.EmployeeDTO;
import com.babel.domain.model.Employee;

public class EmployeeMapper {

	private static final Logger log = LoggerFactory.getLogger(EmployeeMapper.class);
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	private EmployeeMapper() {
		throw new IllegalStateException("The class can't instance, just static methods");
	}

	public static Employee toEntity(EmployeeDTO employeeDTO) {
		LocalDate birtDate = LocalDate.parse(employeeDTO.getBirdDate(), DATE_FORMATTER);
		log.info("Mapper Employee to entity start");
		return new Employee.Builder().setId(employeeDTO.getId()).setFirstName(employeeDTO.getFirstName())
				.setSecondName(employeeDTO.getSecondName()).setFatherLastName(employeeDTO.getFatherLastName())
				.setMothersLastName(employeeDTO.getMothersLastName()).setAge(employeeDTO.getAge())
				.setGender(employeeDTO.getGender()).setBirdDate(birtDate).setWorkStation(employeeDTO.getWorkStation())
				.build();

	}

	public static EmployeeDTO toDTO(Employee employee) {
		String birtDate = employee.getBirdDate().format(DATE_FORMATTER);
		log.info("Mapper Employee to DTO start");
		return new EmployeeDTO.Builder().setId(employee.getId()).setFirstName(employee.getFirstName())
				.setSecondName(employee.getSecondName()).setFatherLastName(employee.getFatherLastName())
				.setMothersLastName(employee.getMothersLastName()).setAge(employee.getAge())
				.setGender(employee.getGender()).setBirdDate(birtDate).setWorkStation(employee.getWorkStation())
				.build();
	}

	public static Employee validateUpdate(Employee employeeExist, EmployeeDTO employeeDTO) {

		Employee.Builder builder = new Employee.Builder().setId(employeeExist.getId())
				.setFirstName(employeeExist.getFirstName()).setSecondName(employeeExist.getSecondName())
				.setFatherLastName(employeeExist.getFatherLastName())
				.setMothersLastName(employeeExist.getMothersLastName()).setAge(employeeExist.getAge())
				.setGender(employeeExist.getGender()).setBirdDate(employeeExist.getBirdDate())
				.setWorkStation(employeeExist.getWorkStation());

		Optional.ofNullable(employeeDTO.getFirstName()).ifPresent(builder::setFirstName);
		Optional.ofNullable(employeeDTO.getSecondName()).ifPresent(builder::setSecondName);
		Optional.ofNullable(employeeDTO.getFatherLastName()).ifPresent(builder::setFatherLastName);
		Optional.ofNullable(employeeDTO.getMothersLastName()).ifPresent(builder::setMothersLastName);
		Optional.ofNullable(employeeDTO.getAge()).ifPresent(builder::setAge);
		Optional.ofNullable(employeeDTO.getGender()).ifPresent(builder::setGender);
		Optional.ofNullable(employeeDTO.getBirdDate()).map(LocalDate::parse).ifPresent(builder::setBirdDate);
		Optional.ofNullable(employeeDTO.getWorkStation()).ifPresent(builder::setWorkStation);
		log.debug("The Id for the new employee update is {}: ",employeeExist.getId());
		return builder.build();
	}

}
