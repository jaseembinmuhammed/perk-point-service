package com.service.perkpoint.auth.user;

import java.util.HashSet;
import java.util.Set;

import com.service.perkpoint.auth.role.PpRole;
import com.service.perkpoint.base.AuditModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class PpUser extends AuditModel {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String mail;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserType userType;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pp_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<PpRole> roles = new HashSet<>();

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PpRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<PpRole> roles) {
		this.roles = roles;
	}

	public void addRole(PpRole role) {
		this.roles.add(role);
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
