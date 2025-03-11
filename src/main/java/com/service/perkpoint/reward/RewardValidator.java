package com.service.perkpoint.reward;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.service.perkpoint.employeeReward.EmployeeAwardRepository;

import jakarta.validation.Valid;

@Component
public class RewardValidator {

	@Autowired
	RewardRepository repo;

	@Autowired
	EmployeeAwardRepository employeeAwardRepository;

	public void validate(@Valid RewardRequest request) {
		if (repo.existsByName(request.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reward with same name exists");
		}
	}

	public void validateDelete(Long rewardId) {
		if (employeeAwardRepository.existsByRewardId(rewardId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Cannot delete this reward, since it is already awarded");
		}
	}

}
