package com.service.perkpoint.reward;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.perkpoint.base.KeyValue;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/reward")
@CrossOrigin("*")
public class RewardController {

	@Autowired
	RewardService service;

	@PostMapping
	public ResponseEntity<RewardResponse> create(@Valid @RequestBody RewardRequest request) {
		RewardResponse response = service.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<KeyValue> list = service.getAll();
		return ResponseEntity.ok(list);
	}

}
