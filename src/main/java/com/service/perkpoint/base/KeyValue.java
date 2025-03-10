package com.service.perkpoint.base;

import com.service.perkpoint.reward.PpReward;

public class KeyValue {

	private Long key;

	private String value;

	public KeyValue(PpReward reward) {
		this.key = reward.getId();
		this.value = reward.getName();
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
