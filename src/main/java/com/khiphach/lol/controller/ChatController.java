package com.khiphach.lol.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.khiphach.lol.model.ChatMessage;

@Controller
public class ChatController {
	
	@MessageMapping("/chat.sendMessage/{topic}")
    @SendTo("/topic/{topic}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable String topic) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{topic}")
    @SendTo("/topic/{topic}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String topic) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
