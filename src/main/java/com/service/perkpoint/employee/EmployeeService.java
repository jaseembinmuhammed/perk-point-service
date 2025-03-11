package com.service.perkpoint.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.service.perkpoint.auth.credential.CredentialService;
import com.service.perkpoint.auth.user.PpUser;
import com.service.perkpoint.auth.user.UserService;
import com.service.perkpoint.auth.user.UserType;
import com.service.perkpoint.base.ServiceLayer;
import com.service.perkpoint.employeeReward.EmployeeRewardService;

import jakarta.validation.Valid;

@Service
public class EmployeeService implements ServiceLayer<PpEmployee> {

	@Autowired
	EmployeeRepository repo;

	@Autowired
	UserService userService;

	@Autowired
	CredentialService credentialService;

	@Autowired
	EmployeeValidator validator;

	@Autowired
	EmployeeRewardService employeeRewardService;

	@Override
	public PpEmployee getById(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Emplyee Id"));
	}

	@Override
	public List<PpEmployee> getByIds(List<Long> ids) {
		return repo.findAllById(ids);
	}

	@Transactional
	public EmployeeResponse create(NewEmployeeRequest request) {
		validator.validate(request);
		request.setUserType(UserType.EMPLOYEE);
		PpUser user = userService.create(request);
		credentialService.create(request, user);

		PpEmployee employee = generateEmployee(request, user);
		return new EmployeeResponse(employee);
	}

	private PpEmployee generateEmployee(NewEmployeeRequest request, PpUser user) {
		PpEmployee employee = new PpEmployee();
		employee.setDepartment(request.getDepartment());
		employee.setUser(user);
		return repo.save(employee);
	}

	public List<EmployeeResponse> get() {
		return repo.findAll().stream().map(EmployeeResponse::new).toList();
	}

	public List<PpEmployee> getAll() {
		return repo.findAll();
	}

	@Transactional
	public void delete(Long employeeId) {
		PpEmployee employee = getById(employeeId);
		credentialService.delete(employee);
		employeeRewardService.delete(employee);
		repo.delete(employee);
	}

	@Transactional
	public EmployeeResponse update(@Valid UpdateEmployeeRequest request) {
		PpEmployee employee = getById(request.getId());
		userService.update(employee, request);

		employee.setDepartment(request.getDepartment());
		employee = repo.save(employee);

		return new EmployeeResponse(employee);
	}

}
