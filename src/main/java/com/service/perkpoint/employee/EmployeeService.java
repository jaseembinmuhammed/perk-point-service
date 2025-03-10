package com.service.perkpoint.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.service.perkpoint.auth.credential.CredentialService;
import com.service.perkpoint.auth.user.PpUser;
import com.service.perkpoint.auth.user.UserService;
import com.service.perkpoint.auth.user.UserType;
import com.service.perkpoint.base.ServiceLayer;

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

	@Override
	public PpEmployee getById(Long id) {
		return null;
	}

	@Override
	public List<PpEmployee> getByIds(List<Long> ids) {
		return null;
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

}
