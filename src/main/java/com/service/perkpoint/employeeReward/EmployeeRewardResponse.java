package com.service.perkpoint.employeeReward;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.service.perkpoint.employee.EmployeeResponse;
import com.service.perkpoint.reward.RewardResponse;

@JsonInclude(Include.NON_NULL)
public class EmployeeRewardResponse {

	private RewardResponse reward;

	private EmployeeResponse employee;

	private LocalDateTime rewardedOn;

	public EmployeeRewardResponse(PpEmployeeReward entry) {
		this.reward = new RewardResponse(entry.getReward());
		this.employee = new EmployeeResponse(entry.getEmployee());
		this.rewardedOn = entry.getCreationDate() == null ? LocalDateTime.now() : entry.getCreationDate();
	}

	public RewardResponse getReward() {
		return reward;
	}

	public void setReward(RewardResponse reward) {
		this.reward = reward;
	}

	public EmployeeResponse getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeResponse employee) {
		this.employee = employee;
	}

	public LocalDateTime getRewardedOn() {
		return rewardedOn;
	}

	public void setRewardedOn(LocalDateTime rewardedOn) {
		this.rewardedOn = rewardedOn;
	}

}
