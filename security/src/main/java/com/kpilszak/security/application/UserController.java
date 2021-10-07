package com.kpilszak.security.application;

import com.kpilszak.security.user.dto.UserDto;
import com.kpilszak.security.user.exception.UserNotFoundException;
import com.kpilszak.security.user.model.User;
import com.kpilszak.security.user.model.repository.UserRepository;
import com.kpilszak.security.user.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserRepository userRepository;
	private final NotificationService notificationService;
	
	public UserController(
			UserRepository userRepository, NotificationService notificationService
	) {
		this.userRepository = userRepository;
		this.notificationService = notificationService;
	}
	
	@PostMapping("/register")
	public void register(@RequestBody UserDto userDto) {
		User user = new User(userDto.getUsername(), userDto.getPassword());
		userRepository.save(user);
		notificationService.sendMessage(userDto);
	}
	
	@PostMapping("/validate")
	public String validateTokenAndGetUsername(@RequestHeader("Authorization") String token) {
		return userRepository.findById(token).orElseThrow(UserNotFoundException::new).getUsername();
	}
}
