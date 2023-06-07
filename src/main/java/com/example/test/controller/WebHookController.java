package com.example.test.controller;

import com.example.test.service.OrderService;
import com.example.test.service.TestGhnService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/webhook")
@Slf4j
public class WebHookController {

    private final SimpMessagingTemplate messagingTemplate;
    public WebHookController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @Autowired
    OrderService orderService;
    @Autowired
    private TestGhnService testGhnService;
    @PostMapping("/poscake")
    public void getposcake(@RequestBody String string) throws JsonProcessingException, ParseException {
        orderService.createOrderByPosCake(string);
        messagingTemplate.convertAndSend("/topic/orders", "create");
        log.info(string);
    }

    @PostMapping("/ghn")
    public void ghn(@RequestBody String string) throws JsonProcessingException, ParseException {
        testGhnService.save(string);
        log.info(string);
    }
}
