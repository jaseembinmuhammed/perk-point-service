package com.service.perkpoint.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateEmployeeRequest {

	@NotNull(message = "Id is Mandatory")
	private Long id;

	@NotBlank(message = "Name is Mandatory")
	private String name;

	@NotBlank(message = "Department is Mandatory")
	private String department;

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

}
