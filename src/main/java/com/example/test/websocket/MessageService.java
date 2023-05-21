package com.example.test.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    public void sendPrivate(Message message){
        simpMessagingTemplate.convertAndSend("/chatroom/" + message.getReceiver() + "/private",message);
    }
    public void sendPublic(Message message){
        log.info("New message: {}",message);
        simpMessagingTemplate.convertAndSend("/chatroom/public",message);
    }
}
