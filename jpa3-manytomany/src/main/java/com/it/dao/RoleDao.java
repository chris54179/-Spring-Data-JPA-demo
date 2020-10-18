package com.it.dao;

import com.it.domain.Role;
import com.it.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleDao extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {
}
