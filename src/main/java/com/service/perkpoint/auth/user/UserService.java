package com.service.perkpoint.auth.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.service.perkpoint.auth.role.PpRole;
import com.service.perkpoint.auth.role.RoleService;
import com.service.perkpoint.auth.role.RoleSet;
import com.service.perkpoint.base.ServiceLayer;
import com.service.perkpoint.employee.NewEmployeeRequest;

@Service
public class UserService implements ServiceLayer<PpUser> {

	@Autowired
	PpUserRepo repo;

	@Autowired
	RoleService roleService;

	@Override
	public PpUser getById(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User Id"));
	}

	@Override
	public List<PpUser> getByIds(List<Long> ids) {
		return repo.findAllById(ids);
	}

	public PpUser create(NewEmployeeRequest request) {
		PpRole role = roleService.getRoleByName(RoleSet.EMPLOYEE, true);

		PpUser user = new PpUser();
		user.setName(request.getName());
		user.addRole(role);
		user.setUserType(request.getUserType());
		user.setMail(request.getMail());

		return repo.save(user);
	}

}
