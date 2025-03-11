package com.service.perkpoint.employeeReward;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.perkpoint.employee.PpEmployee;

public interface EmployeeAwardRepository extends JpaRepository<PpEmployeeReward, Long> {

	void deleteAllByEmployee(PpEmployee employee);

}
