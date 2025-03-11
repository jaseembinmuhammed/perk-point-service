package com.service.perkpoint.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.service.perkpoint.auth.user.PpUserRepo;

@Component
public class EmployeeValidator {

	@Autowired
	PpUserRepo userRepo;

	public void validate(NewEmployeeRequest request) {
		if (userRepo.existsByName(request.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee With Same Name Exists");
		}
		if (userRepo.existsByMail(request.getMail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee With Same Mail Id Exists");
		}
	}

}
