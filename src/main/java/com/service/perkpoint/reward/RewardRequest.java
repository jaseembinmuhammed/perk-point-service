package com.service.perkpoint.reward;

import jakarta.validation.constraints.NotBlank;

public class RewardRequest {

	@NotBlank(message = "Name is mandatory")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
