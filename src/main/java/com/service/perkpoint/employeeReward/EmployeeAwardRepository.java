package com.service.perkpoint.employeeReward;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.service.perkpoint.employee.PpEmployee;

public interface EmployeeAwardRepository extends JpaRepository<PpEmployeeReward, Long> {

	void deleteAllByEmployee(PpEmployee employee);

	boolean existsByRewardId(Long rewardId);

	@Query("SELECT REWARD FROM PpEmployeeReward REWARD WHERE REWARD.employee.user.id=:id")
	List<PpEmployeeReward> getMyRewards(Long id);

}
