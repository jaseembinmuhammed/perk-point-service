package com.service.perkpoint.reward;

import com.service.perkpoint.base.AuditModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PpReward extends AuditModel {

	@Column(nullable = false, unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
