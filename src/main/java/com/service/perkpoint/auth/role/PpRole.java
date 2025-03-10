package com.service.perkpoint.auth.role;

import java.util.Objects;

import com.service.perkpoint.base.AuditModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class PpRole extends AuditModel {

	@Enumerated(EnumType.STRING)
	@Column(unique = true, nullable = false)
	private RoleSet name;

	public RoleSet getName() {
		return name;
	}

	public void setName(RoleSet name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PpRole other = (PpRole) obj;
		return name == other.name;
	}

}
