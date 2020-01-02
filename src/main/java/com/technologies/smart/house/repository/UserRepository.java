package com.technologies.smart.house.repository;

import com.technologies.smart.house.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
