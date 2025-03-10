package com.service.perkpoint.reward;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<PpReward, Long> {

	boolean existsByName(String name);

}
