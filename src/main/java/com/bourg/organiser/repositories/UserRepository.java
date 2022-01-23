package com.bourg.organiser.repositories;

import com.bourg.organiser.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}