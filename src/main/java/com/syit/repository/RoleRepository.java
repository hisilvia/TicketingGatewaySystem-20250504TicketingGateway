package com.syit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syit.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRoleName(String roleName);
}
