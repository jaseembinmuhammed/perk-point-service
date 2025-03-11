package com.service.perkpoint.auth.credential;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.perkpoint.auth.user.PpUser;

public interface PpCredentialRepository extends JpaRepository<PpCredential, Long> {

	Optional<PpCredential> findByMail(String userName);

	void deleteByUser(PpUser user);

}
