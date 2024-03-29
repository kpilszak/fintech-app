package com.kpilszak.lendingengine.domain.repository;

import com.kpilszak.lendingengine.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
