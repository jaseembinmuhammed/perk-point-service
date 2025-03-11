package com.service.perkpoint.employeeReward;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.service.perkpoint.base.ServiceLayer;
import com.service.perkpoint.employee.EmployeeResponse;
import com.service.perkpoint.employee.EmployeeService;
import com.service.perkpoint.employee.PpEmployee;
import com.service.perkpoint.reward.PpReward;
import com.service.perkpoint.reward.RewardResponse;
import com.service.perkpoint.reward.RewardService;

@Service
public class EmployeeRewardService implements ServiceLayer<PpEmployeeReward> {

	@Autowired
	EmployeeAwardRepository repo;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	RewardService rewardService;

	@Override
	public PpEmployeeReward getById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Id"));
	}

	@Override
	public List<PpEmployeeReward> getByIds(List<Long> ids) {
		return repo.findAllById(ids);
	}

	@Transactional
	public EmployeeRewardResponse assignAward(AssignAwardRequest request) {
		PpEmployeeReward reward = generateEntity(request);
		rewardService.updateOnAssignment(reward.getReward());
		return new EmployeeRewardResponse(reward);
	}

	private PpEmployeeReward generateEntity(AssignAwardRequest request) {
		PpEmployee employee = employeeService.getById(request.getEmployeeId());
		PpReward reward = rewardService.getById(request.getAwardId());

		PpEmployeeReward entity = new PpEmployeeReward();
		entity.setEmployee(employee);
		entity.setReward(reward);
		return repo.save(entity);
	}

	public List<RewardedDetailsResponse> getEmployeeRewardDetails() {
		List<PpEmployee> employees = employeeService.getAll();
		List<PpEmployeeReward> rewards = repo.findAll();
		return generateRewardedDetailsResponse(employees, rewards);
	}

	private List<RewardedDetailsResponse> generateRewardedDetailsResponse(List<PpEmployee> employees,
			List<PpEmployeeReward> rewards) {
		var employeeRewardMap = rewards.stream().collect(Collectors.groupingBy(entry -> entry.getEmployee().getId()));
		return employees.stream().map(employee -> generateDetails(employee, employeeRewardMap.get(employee.getId())))
				.toList();
	}

	private RewardedDetailsResponse generateDetails(PpEmployee employee, List<PpEmployeeReward> list) {
		RewardedDetailsResponse response = new RewardedDetailsResponse();
		if (employee != null) {
			response.setEmployee(new EmployeeResponse(employee));
		}
		if (list != null && !list.isEmpty()) {
			var rewards = list.stream().map(entry -> new RewardResponse(entry.getReward(), entry.getCreationDate()))
					.toList();
			response.setRewards(rewards);
		}
		return response;
	}

	public void delete(PpEmployee employee) {
		repo.deleteAllByEmployee(employee);
	}

}
