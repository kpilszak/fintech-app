package com.kpilszak.security.user.model.repository;

import com.kpilszak.security.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
