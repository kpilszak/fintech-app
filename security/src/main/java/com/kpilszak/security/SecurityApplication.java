package com.kpilszak.security;

import com.kpilszak.security.user.model.User;
import com.kpilszak.security.user.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("John", "12345"));
		userRepository.save(new User("Mindaugas", "12345"));
	}
}
