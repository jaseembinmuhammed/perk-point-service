package com.service.perkpoint.employee;

import com.service.perkpoint.auth.user.PpUser;
import com.service.perkpoint.base.AuditModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class PpEmployee extends AuditModel {

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private PpUser user;

	@Column(columnDefinition = "text")
	private String department;

	public PpUser getUser() {
		return user;
	}

	public void setUser(PpUser user) {
		this.user = user;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
