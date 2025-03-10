package com.service.perkpoint.employee;

import com.service.perkpoint.auth.user.UserType;

import jakarta.validation.constraints.NotBlank;

public class NewEmployeeRequest {

	@NotBlank(message = "Name is Mandatory")
	private String name;

	@NotBlank(message = "Department is Mandatory")
	private String department;

	@NotBlank(message = "Mail is Mandatory")
	private String mail;

	private UserType userType;

	@NotBlank(message = "Password is Mandatory")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
