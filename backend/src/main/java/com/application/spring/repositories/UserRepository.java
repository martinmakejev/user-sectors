package com.application.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.application.spring.model.User;
import com.application.spring.model.UserSession;

public interface UserRepository extends JpaRepository<User, Long> {
    User findBySession(UserSession sessionToken);
}
