package org.springframework.web.socket;


import org.chtijbug.spring.websocket.event.MyMessage;

public class MyMessageWSMessage extends AbstractWebSocketMessage<MyMessage>  {


    MyMessageWSMessage(MyMessage payload) {
        super(payload, true);
    }

    @Override
    public int getPayloadLength() {
        return getPayload().toString().getBytes().length;
    }

    @Override
    protected String toStringPayload() {
        return getPayload().toString();
    }
}
