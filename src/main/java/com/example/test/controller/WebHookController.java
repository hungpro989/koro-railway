package com.example.test.controller;

import com.example.test.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/webhook/poscake")
public class WebHookController {
    @Autowired
    OrderService orderService;
    @PostMapping()
    public void getposcake(@RequestBody String string) throws JsonProcessingException, ParseException {
        orderService.createOrderByPosCake(string);
    }
}
