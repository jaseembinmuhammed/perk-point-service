package com.service.perkpoint.employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<PpEmployee, Long> {

}
