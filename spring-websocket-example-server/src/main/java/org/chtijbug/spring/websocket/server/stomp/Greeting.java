package org.chtijbug.spring.websocket.server.stomp;

public class Greeting {

    private String content;

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
