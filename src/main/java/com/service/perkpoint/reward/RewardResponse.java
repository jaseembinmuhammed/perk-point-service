package com.service.perkpoint.reward;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RewardResponse {

	private Long id;

	private String name;

	private LocalDateTime createdAt;

	private LocalDateTime rewardedTime;

	private Boolean rewarded;

	public RewardResponse(PpReward e) {
		this.id = e.getId();
		this.name = e.getName();
		this.createdAt = e.getCreationDate() != null ? e.getCreationDate() : LocalDateTime.now();
		this.rewarded = e.getRewarded();
	}

	public RewardResponse(PpReward e, LocalDateTime time) {
		this.id = e.getId();
		this.name = e.getName();
		this.rewardedTime = time;
		this.rewarded = e.getRewarded();
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getRewardedTime() {
		return rewardedTime;
	}

	public void setRewardedTime(LocalDateTime rewardedTime) {
		this.rewardedTime = rewardedTime;
	}

	public Boolean getRewarded() {
		return rewarded;
	}

	public void setRewarded(Boolean rewarded) {
		this.rewarded = rewarded;
	}
}
