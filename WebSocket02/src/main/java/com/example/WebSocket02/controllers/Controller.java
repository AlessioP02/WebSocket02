package com.example.WebSocket02.controllers;

import com.example.WebSocket02.DTO.ClientMessageDTO;
import com.example.WebSocket02.DTO.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/broadcast-message")
    public ResponseEntity sendNotificationToClient(@RequestBody MessageDTO messageDTO) {
        simpMessagingTemplate.convertAndSend("/topic/broadcast", messageDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @MessageMapping( "/client-message")
    @SendTo("/topic/broadcast")
    public MessageDTO sendNotificationToServer(ClientMessageDTO clientMessageDTO){
        System.out.println(clientMessageDTO.toString());
        return new MessageDTO(clientMessageDTO.getClientName(),clientMessageDTO.getClientAlert(),clientMessageDTO.getClientMsg());

    }
}
