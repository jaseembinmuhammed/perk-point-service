package com.service.perkpoint.reward;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.service.perkpoint.auth.AuthUtil;
import com.service.perkpoint.auth.user.PpUser;
import com.service.perkpoint.base.KeyValue;
import com.service.perkpoint.base.ServiceLayer;

import jakarta.validation.Valid;

@Component
public class RewardService implements ServiceLayer<PpReward> {

	@Autowired
	RewardRepository repo;

	@Autowired
	RewardValidator validator;

	@Override
	public PpReward getById(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid reward id"));
	}

	@Override
	public List<PpReward> getByIds(List<Long> ids) {
		return repo.findAllById(ids);
	}

	@Transactional
	public RewardResponse create(@Valid RewardRequest request) {
		validator.validate(request);
		PpReward reward = generateReward(request);
		return new RewardResponse(reward);
	}

	private PpReward generateReward(@Valid RewardRequest request) {
		PpReward reward = new PpReward();
		reward.setName(request.getName());
		return repo.save(reward);
	}

	public List<RewardResponse> getAll() {
		return repo.findAll().stream().map(RewardResponse::new).toList();
	}

	public List<KeyValue> getKeyValues() {
		return repo.findAll().stream().map(KeyValue::new).toList();
	}

	@Transactional
	public void delete(Long rewardId) {
		validator.validateDelete(rewardId);
		repo.deleteById(rewardId);
	}

	public void updateOnAssignment(PpReward reward) {
		reward.setRewarded(true);
		repo.save(reward);
	}

	public RewardResponse update(@Valid UpdateRewardRequest request) {
		PpReward reward = getById(request.getId());
		reward.setName(request.getName());
		reward = repo.save(reward);
		return new RewardResponse(reward);
	}

}
