package com.service.perkpoint.base;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.service.perkpoint.auth.AuthUtil;
import com.service.perkpoint.auth.user.PpUser;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;

@MappedSuperclass
public class AuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_sequence")
	@SequenceGenerator(name = "custom_sequence", sequenceName = "my_sequence", allocationSize = 1, initialValue = 100000)
	private Long id;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime creationDate;

	@UpdateTimestamp
	private LocalDateTime lastUpdated;

	@ManyToOne
	private PpUser createdBy;

	private Boolean active = true;

	@PrePersist
	void beforePersist() {
		this.createdBy = AuthUtil.getLoggedInUser();
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public PpUser getCreatedBy() {
		return createdBy;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
