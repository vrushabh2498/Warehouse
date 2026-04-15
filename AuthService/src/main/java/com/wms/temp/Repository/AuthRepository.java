package com.wms.temp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wms.temp.entity.UserEntity;

public interface AuthRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
