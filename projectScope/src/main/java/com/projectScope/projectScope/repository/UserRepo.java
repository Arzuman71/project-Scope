package com.projectScope.projectScope.repository;


import com.projectScope.projectScope.model.User;
import com.projectScope.projectScope.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllByType(Type type);
}
