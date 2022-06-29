package com.springbackend.Springbackend;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbackend.Springbackend.dao.EmployeeRepository;
import com.springbackend.Springbackend.dao.UserRepository;
import com.springbackend.Springbackend.model.Employee;
import com.springbackend.Springbackend.service.EmployeeService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class EmployeeControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testGetEmployees() throws Exception {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setFirstName("ssai");
		employee.setLastName("sri");
		employee.setEmailId("sri@gmail.com");

		List<Employee> allEmployees = new ArrayList<>();
		allEmployees.add(employee);

		Mockito.when(employeeService.fetchEmployees()).thenReturn(allEmployees);

		System.out.println("test method");
		mvc.perform(get("/api/v1/getAllEmployees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].firstName", Matchers.equalTo(employee.getFirstName())));
	}

	@Test
	//@Ignore
	public void testSaveEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setFirstName("sai");
		employee.setLastName("sri");
		employee.setEmailId("sri@gmail.com");
		Mockito.when(employeeService.saveEmployee(ArgumentMatchers.any())).thenReturn(employee);
		String json = mapper.writeValueAsString(employee);
		mvc.perform(post("/api/v1/saveEmployee").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	
	
	@Test
	public void testGetEmployeById() throws Exception {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setFirstName("sai");
		employee.setLastName("sri");
		employee.setEmailId("sri@gmail.com");
		Mockito.when(employeeService.getEmployee(ArgumentMatchers.anyInt())).thenReturn(employee);
	mvc.perform(get("/api/v1/getEmployee/{id}",1)
		      .contentType(MediaType.APPLICATION_JSON))
		      .andExpect(status().isOk());
	}
	@Test
    public void testDeleteEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setFirstName("sai");
		employee.setLastName("sri");
		employee.setEmailId("sri@gmail.com");
		Mockito.when(employeeService.getEmployee(ArgumentMatchers.anyInt())).thenReturn(employee);
        employeeService.deleteEmployee(ArgumentMatchers.anyInt());
        MvcResult requestResult = mvc.perform(delete("/api/v1/deleteEmployee/1"))
        		.andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
    assertEquals(result, "Employee deleted successfully");
    }
	/**
	 * @throws Exception
	 */

/*	@Test
	public void testUpdateEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setFirstName("Minu");
		employee.setLastName("sri");
		employee.setEmailId("minu@gmail.com");
		Mockito.when(employeeService.getEmployee(ArgumentMatchers.anyInt())).thenReturn(employee);
		//Mockito.when(employeeService.updateEmployee(employee));
		String json = mapper.writeValueAsString(employee);
		mvc.perform(put("/api/v1/updateEmployee/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
			.andExpect(jsonPath("$.id", Matchers.equalTo(1)))
				.andExpect(jsonPath("$.firstName", Matchers.equalTo("Minu")));
	}*/

}











//https://asbnotebook.com/spring-boot-rest-controller-junit-test-example/