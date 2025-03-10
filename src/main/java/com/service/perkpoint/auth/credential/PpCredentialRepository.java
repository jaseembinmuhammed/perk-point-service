package com.service.perkpoint.auth.credential;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PpCredentialRepository extends JpaRepository<PpCredential, Long> {

	Optional<PpCredential> findByMail(String userName);

}
