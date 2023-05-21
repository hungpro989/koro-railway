package com.example.test.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @MessageMapping("messages")
    public void receivePublicMessage(@Payload Message message){
        log.info("New message: {}",message);
        messageService.sendPublic(message);
    }
    @MessageMapping("private-messages")
    public void receivePrivateMessage(@Payload Message message){
        log.info("New private message: {}",message);
        messageService.sendPrivate(message);
    }

}
