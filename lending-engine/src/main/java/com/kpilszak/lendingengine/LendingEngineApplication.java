package com.kpilszak.lendingengine;

import com.kpilszak.lendingengine.domain.model.User;
import com.kpilszak.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LendingEngineApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LendingEngineApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("John", "John", "B", 27, "Software Developer"));
		userRepository.save(new User("Peter", "Peter", "C", 21, "Pilot"));
		userRepository.save(new User("Henry", "Henry", "E", 21, "Unemployed"));
	}
}
