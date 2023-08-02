package com.elleined.locationapi.repository;

import com.elleined.locationapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}