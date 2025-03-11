package com.service.perkpoint.auth.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<PpRole, Long> {

	PpRole findByName(RoleSet role);

}
