package com.service.perkpoint.auth.credential;

import com.service.perkpoint.auth.user.PpUser;
import com.service.perkpoint.base.AuditModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class PpCredential extends AuditModel {

	@Column(unique = true)
	private String mail;

	@Column(columnDefinition = "text")
	private String password;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private PpUser user;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PpUser getUser() {
		return user;
	}

	public void setUser(PpUser user) {
		this.user = user;
	}

}
