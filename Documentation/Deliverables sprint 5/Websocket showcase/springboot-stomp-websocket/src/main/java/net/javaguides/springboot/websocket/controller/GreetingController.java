package net.javaguides.springboot.websocket.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

import java.io.*;
import java.lang.Thread;
import net.javaguides.springboot.websocket.model.Greeting;
import net.javaguides.springboot.websocket.model.HelloMessage;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public HelloMessage greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return message;
    }

}
