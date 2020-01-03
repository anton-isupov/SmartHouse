package com.technologies.smart.house.repository;

import com.technologies.smart.house.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
