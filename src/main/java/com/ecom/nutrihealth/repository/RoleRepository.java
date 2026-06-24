package com.ecom.nutrihealth.repository;


import com.ecom.nutrihealth.model.AppRole;
import com.ecom.nutrihealth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(AppRole appRole);
}