package com.service.perkpoint.employeeReward;

import com.service.perkpoint.base.AuditModel;
import com.service.perkpoint.employee.PpEmployee;
import com.service.perkpoint.reward.PpReward;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class PpEmployeeReward extends AuditModel {

	@ManyToOne(optional = false)
	private PpEmployee employee;

	@ManyToOne(optional = false)
	private PpReward reward;

	public PpEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(PpEmployee employee) {
		this.employee = employee;
	}

	public PpReward getReward() {
		return reward;
	}

	public void setReward(PpReward reward) {
		this.reward = reward;
	}

}
