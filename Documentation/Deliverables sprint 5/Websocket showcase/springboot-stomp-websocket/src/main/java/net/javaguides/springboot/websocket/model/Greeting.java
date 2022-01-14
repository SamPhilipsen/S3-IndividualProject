package net.javaguides.springboot.websocket.model;

import org.apache.logging.log4j.message.Message;

public class Greeting {

    private HelloMessage content;

    public Greeting() {
    }

    public Greeting(HelloMessage content) {
        this.content = content;
    }

    public HelloMessage getContent() {
        return content;
    }

}
