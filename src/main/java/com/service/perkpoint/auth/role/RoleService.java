package com.service.perkpoint.auth.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.service.perkpoint.base.ServiceLayer;

@Service
public class RoleService implements ServiceLayer<PpRole> {

	@Autowired
	RoleRepository repo;

	@Override
	public PpRole getById(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Role Id"));
	}

	@Override
	public List<PpRole> getByIds(List<Long> ids) {
		return repo.findAllById(ids);
	}

	public PpRole getRoleByName(RoleSet role, boolean isMandatory) {
		PpRole ppRole = repo.findByName(role);
		if (isMandatory && ppRole == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role Not Found");
		}
		return ppRole;
	}

}
