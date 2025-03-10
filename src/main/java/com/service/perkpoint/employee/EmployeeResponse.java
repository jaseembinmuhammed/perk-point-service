package com.service.perkpoint.employee;

public class EmployeeResponse {

	private Long id;

	private String name;

	private String department;

	private String email;

	public EmployeeResponse(PpEmployee employee) {
		this.id = employee.getId();
		this.name = employee.getUser().getName();
		this.department = employee.getDepartment();
		this.email = employee.getUser().getMail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
