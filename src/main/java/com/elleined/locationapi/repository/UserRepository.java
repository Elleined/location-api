package com.elleined.locationapi.repository;

import com.elleined.locationapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUUID(String UUID);
}