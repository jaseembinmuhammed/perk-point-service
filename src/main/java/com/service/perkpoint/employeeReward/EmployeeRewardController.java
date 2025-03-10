package com.service.perkpoint.employeeReward;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/employeeReward")
public class EmployeeRewardController {

	@Autowired
	EmployeeRewardService service;

	@PostMapping
	public ResponseEntity<?> assignAward(@RequestBody AssignAwardRequest request) {
		EmployeeRewardResponse reponse = service.assignAward(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
	}

	@GetMapping
	public ResponseEntity<?> getEmployeeRewardDetails() {
		List<RewardedDetailsResponse> list = service.getEmployeeRewardDetails();
		return ResponseEntity.ok(list);
	}

}
