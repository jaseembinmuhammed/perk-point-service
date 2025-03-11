package com.service.perkpoint.auth.credential;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.service.perkpoint.auth.user.PpUser;
import com.service.perkpoint.base.ServiceLayer;
import com.service.perkpoint.employee.NewEmployeeRequest;
import com.service.perkpoint.employee.PpEmployee;

@Service
public class CredentialService implements ServiceLayer<PpCredential> {

	@Autowired
	PpCredentialRepository repo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public PpCredential getById(Long id) {
		return null;
	}

	@Override
	public List<PpCredential> getByIds(List<Long> ids) {
		return null;
	}

	public PpCredential create(NewEmployeeRequest request, PpUser user) {
		PpCredential credential = new PpCredential();
		credential.setMail(request.getMail());
		credential.setUser(user);
		credential.setPassword(passwordEncoder.encode(request.getPassword()));
		return repo.save(credential);
	}

	public void delete(PpEmployee employee) {
		repo.deleteByUser(employee.getUser());
	}

}
