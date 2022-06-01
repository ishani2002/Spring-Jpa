package com.hcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.modal.Employee;
import com.hcl.repositories.EnployeeRepository;

@RestController
public class EmployeeController {
	@Autowired
	private  EnployeeRepository enployeeRepository;

	@PostMapping("/save")
	 public Employee createOrSave(@RequestBody Employee employee) {
	return enployeeRepository.save(employee);
}
	
	
	@GetMapping("/all")
	public List<Employee> getAllEmployees(){
		return (List<Employee>)enployeeRepository.findAll();
	}
	@GetMapping("/employees/{id}")
	  Employee getEmployeeById(@PathVariable Long id) {
	    return enployeeRepository.findById(id).get();
	  }
	
	 
	  @DeleteMapping("/employees/{id}")
	  void deleteEmployee(@PathVariable Long id) {
	    enployeeRepository.deleteById(id);
	  }

	  @PutMapping("/employees/{id}")
	  Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
	 
	    return enployeeRepository.findById(id).map(employee -> {
	      employee.setName(newEmployee.getName());
	      employee.setId(newEmployee.getId());
	      employee.setJob(newEmployee.getJob());
	      employee.setSalary(newEmployee.getSalary());
	      return enployeeRepository.save(employee);
	    }).orElseGet(() -> {
	      newEmployee.setId(id);
	      return enployeeRepository.save(newEmployee);
	    });
	  }
	  
	  
	  /*@PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable long id) {

 

        Optional<Employee> EmployeeOptional = employeeRepository.findById(id);

 

        if (!EmployeeOptional.isPresent())
            return ResponseEntity.notFound().build();

 

        employee.setId(id);
        
        employeeRepository.save(employee);

 

        return ResponseEntity.noContent().build();
    }
 */
}






