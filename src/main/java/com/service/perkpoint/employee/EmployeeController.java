package com.service.perkpoint.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/employee")
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@PostMapping
	public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody NewEmployeeRequest request) {
		EmployeeResponse reponse = service.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
	}

	@PutMapping
	public ResponseEntity<EmployeeResponse> update(@Valid @RequestBody UpdateEmployeeRequest request) {
		EmployeeResponse reponse = service.update(request);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeResponse>> get() {
		List<EmployeeResponse> response = service.get();
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("{employeeId}")
	public ResponseEntity<?> delete(@PathVariable Long employeeId) {
		service.delete(employeeId);
		return ResponseEntity.noContent().build();
	}

}
