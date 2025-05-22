package com.babel;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.babel.application.service.EmployeeService;
import com.babel.domain.dto.EmployeeDTO;
import com.babel.domain.model.Employee;
import com.babel.exception.GlobalExceptionHandler;
import com.babel.web.controller.EmployeeController;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest  
@Import(GlobalExceptionHandler.class)
public class EmployeeControllerTest {

	 private MockMvc mockMvc;

	    @Mock
	    private EmployeeService employeeService;

	    @InjectMocks
	    private EmployeeController employeeController;
	    

	    @BeforeEach
	    public void setUp() {
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(employeeController)
	                .setControllerAdvice(new GlobalExceptionHandler()) // ← esto
	                .build();
	    }
	    
	    @Test
	    public void contextLoads() {
	    	
	    }
	    
	    /**
	     * Test method to create an employee
	     * @throws Exception
	     */
	    @Test
	    public void testCreateEmployee() throws Exception {
	        EmployeeDTO employee = new EmployeeDTO.Builder()
	                .setFirstName("John")
	                .setSecondName("Smith")
	                .setFatherLastName("Doe")
	                .setMothersLastName("jackson")
	                .setAge(25)
	                .setGender("Female")
	                .setBirdDate("22-08-1991")
	                .setWorkStation("Developer")
	                .build();

	        mockMvc.perform(post("/api/v1/employees")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(Arrays.asList(employee))))
	                .andExpect(status().isCreated()); 
	    }
	    
	    /**
	     * Method to created employee with bad request
	     * @throws Exception
	     */
	    @Test
	    public void testCreateEmployee_BadRequest() throws Exception {
	    	EmployeeDTO employee = new EmployeeDTO.Builder()
	                .setFirstName(null)  
	                .setAge(25)
	                .setGender(null)
	                .setBirdDate("21-12-1998")
	                .setWorkStation("Tester")
	                .build();

	        mockMvc.perform(post("/api/v1/employees")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(employee)))
	                .andExpect(status().isBadRequest());  
	    }

	    
	    /**
	     * Method test getAllEmployees, verify if return one employee his first name is ****
	     * @throws Exception
	     */
	    @Test
	    public void testGetAllEmployees_EmployeeOnList() throws Exception {

	        Employee employee=  new Employee.Builder()
	        		.setId(37l)
					.setFirstName("carlos")
					.setSecondName("ad")
					.setFatherLastName("ad")
					.setMothersLastName("ad")
					.setAge(25)
					.setGender("masculino")
					.setBirdDate(LocalDate.parse("2025-12-22"))
					.setWorkStation("desarrollador")
					.build();

	       
	        when(employeeService.getEmployees()).thenReturn(Arrays.asList(employee));

	        mockMvc.perform(get("/api/v1/employees"))
	                .andExpect(status().isOk())  
	                .andExpect(jsonPath("$[?(@.firstName == 'carlos')]").exists());
	    }
	    
	    /**
	     * This method test when table employee from bd is void;
	     * @throws Exception
	     */
	    @Test
	    public void testGetAllEmployees_EmployeesNoFound() throws Exception {
	    	   when(employeeService.getEmployees()).thenReturn(Collections.emptyList());

	    	    mockMvc.perform(get("/api/v1/employees"))
	    	            .andExpect(status().isNotFound())
	    	            .andExpect(jsonPath("$").value("The employees not found."));  
	    }
	    
	    
	    @Test
	    public void testUpdateEmployee() throws Exception {

	        Employee employee = new Employee.Builder()
	                .setId(3L)
	                .setFirstName("John")
	                .setSecondName("Smith")
	                .setFatherLastName("Doe")
	                .setMothersLastName("Jackson")
	                .setAge(25)
	                .setGender("Masculine")
	                .setBirdDate(LocalDate.parse("1991-08-22"))
	                .setWorkStation("Developer")
	                .build();
	        
	        EmployeeDTO updatedEmployee = new EmployeeDTO.Builder()
	                .setFirstName("Carlos Updated")
	                .build();

	    	   when(employeeService.getEmployeeById(3L)).thenReturn(Optional.of(employee)); 
	    	    doNothing().when(employeeService).updateEmployee(employee);
	        mockMvc.perform(patch("/api/v1/employees/{id}", 3L)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(updatedEmployee)))
	                .andExpect(status().isOk());
	    }

	    @Test
	    public void testUpdateEmployee_BadRequest() throws Exception {
	    	
	        Employee employee = new Employee.Builder()
	                .setId(3L)
	                .setFirstName("John")
	                .setSecondName("Smith")
	                .setFatherLastName("Doe")
	                .setMothersLastName("Jackson")
	                .setAge(25)
	                .setGender("Masculine")
	                .setBirdDate(LocalDate.parse("1991-08-22"))
	                .setWorkStation("Developer")
	                .build();
	        

	    	   when(employeeService.getEmployeeById(3L)).thenReturn(Optional.of(employee)); 
	    	    doNothing().when(employeeService).updateEmployee(employee);
	    	    
	        EmployeeDTO employeeWithBadData = new EmployeeDTO.Builder()
	                .setAge(325)
	                .setGender("Masculine")
	                .setWorkStation("Developer")
	                .build();

	        mockMvc.perform(patch("/api/v1/employees/{id}", 3L)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(employeeWithBadData)))
	                .andExpect(status().isBadRequest()); 
	    }
	    
	    @Test
	    public void testDeleteEmployee() throws Exception {
	        Employee employee = new Employee.Builder()
	                .setId(35L)
	                .setFirstName("John")
	                .setSecondName("Smith")
	                .setFatherLastName("Doe")
	                .setMothersLastName("Jackson")
	                .setAge(25)
	                .setGender("Female")
	                .setBirdDate(LocalDate.parse("1991-08-22"))
	                .setWorkStation("Developer")
	                .build();
	        
	    	   when(employeeService.getEmployeeById(35L)).thenReturn(Optional.of(employee));  
	    	    doNothing().when(employeeService).deleteEmployee(35L); 

	        mockMvc.perform(delete("/api/v1/employees/{id}", 35L))
	                .andExpect(status().isNoContent());  
	    }

	    @Test
	    public void testDeleteEmployee_NotFound() throws Exception {
	    	when(employeeService.getEmployeeById(999L)).thenReturn(Optional.empty());

	        mockMvc.perform(delete("/api/v1/employees/{id}", 999L))  
	                .andExpect(status().isNotFound());  
	    }
	    
	    /**
	     * Method test an invalid a URL 
	     * @throws Exception
	     */
	    @Test
	    public void testInvalidUrl()throws Exception {
	    	   mockMvc.perform(get("/api/v1/invalid-url"))
	            .andExpect(status().isNotFound()); 
	    }
}
