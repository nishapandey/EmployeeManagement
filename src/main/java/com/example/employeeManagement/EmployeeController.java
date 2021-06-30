package com.example.employeeManagement;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	
	@GetMapping("/employees")
	public List<EmployeeEntity> getEmployees(){
		return service.getEmployees();
	} 
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeEntity> getEmployee(@PathVariable Long id)throws ResourceNotFoundException {
		EmployeeEntity employee = service.getEmployee(id);
		if(employee == null)
	    	   throw new ResourceNotFoundException("Employee not found for this id :: " + id);

        return ResponseEntity.ok().body(employee);
    }
	
	@PostMapping("/employees")
	public ResponseEntity<EmployeeEntity> updateEmployee(@Valid @RequestBody EmployeeEntity entity){
		
		final EmployeeEntity updatedEmployee = service .saveEmployee(entity);
        return ResponseEntity.ok(updatedEmployee);
		  
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeEntity> saveEmployee(
			@PathVariable(value = "id") Long employeeId, @Valid @RequestBody EmployeeEntity employeeDetails
			) 
			throws ResourceNotFoundException {
			EmployeeEntity employee = service.getEmployee(employeeId);
			if(employee == null)
		    	   throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);

	        employee.setEmail(employeeDetails.getEmail());
	        employee.setEmail(employeeDetails.getEmail());
	        employee.setFirstname(employeeDetails.getFirstname());
	        final EmployeeEntity updatedEmployee = service .saveEmployee(employee);
	        return ResponseEntity.ok(updatedEmployee);
	        
	        
	    }
	
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<EmployeeEntity> deleteEmployee(@PathVariable Long id)
	         throws ResourceNotFoundException {
	        EmployeeEntity employee = service.getEmployee(id);
	        if(employee == null)
	    	   throw new ResourceNotFoundException("Employee not found for this id :: " + id);

	        service.deleteEmployee(id);
	        return ResponseEntity.ok().build();
	   
	}
	
	

}
