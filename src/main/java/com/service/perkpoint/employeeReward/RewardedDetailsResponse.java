package com.service.perkpoint.employeeReward;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.service.perkpoint.employee.EmployeeResponse;
import com.service.perkpoint.reward.RewardResponse;

@JsonInclude(Include.NON_NULL)
public class RewardedDetailsResponse {

	private EmployeeResponse employee;

	private List<RewardResponse> rewards = new ArrayList<>();

	public EmployeeResponse getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeResponse employee) {
		this.employee = employee;
	}

	public List<RewardResponse> getRewards() {
		return rewards;
	}

	public void setRewards(List<RewardResponse> rewards) {
		this.rewards = rewards;
	}

}
