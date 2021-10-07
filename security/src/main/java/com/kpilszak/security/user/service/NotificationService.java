package com.kpilszak.security.user.service;

import com.google.gson.Gson;
import com.kpilszak.security.user.dto.UserDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
	
	private final RabbitTemplate rabbitTemplate;
	private static final Gson GSON = new Gson();
	
	@Autowired
	public NotificationService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(UserDto userDto) {
		userDto.setPassword(null);
		rabbitTemplate.convertAndSend("userRegisteredTopic", "user.registered", GSON.toJson(userDto));
	}
}
