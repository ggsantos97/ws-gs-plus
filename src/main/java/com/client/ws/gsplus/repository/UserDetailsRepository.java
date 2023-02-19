package com.client.ws.gsplus.repository;

import com.client.ws.gsplus.model.jpa.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserCredentials, Long> {

    Optional<UserCredentials> findByUsername(String name);
}
