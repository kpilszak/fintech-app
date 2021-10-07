package com.kpilszak.profile.domain.repository;

import com.kpilszak.profile.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
