package net.javaguides.springboot.websocket.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class HelloMessage {

    private String text;
    private String username;
    private String textcolor;
    private LocalTime timeStamp = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);

    public HelloMessage() {
    }

    public HelloMessage(String username, String text, String textcolor) {
        this.text = text;
        this.username = username;
        this.textcolor = textcolor;
    }

    public String getContent() {
        return timeStamp + " " + username + ": " + text;
    }

    public void setContent(String text, String username) {
        this.text = text;
        this.username = username;
        this.textcolor = textcolor;
    }
}
