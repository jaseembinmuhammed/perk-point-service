package com.service.perkpoint.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PpUserRepo extends JpaRepository<PpUser, Long> {

	boolean existsByName(String name);

	boolean existsByMail(String mail);

}
