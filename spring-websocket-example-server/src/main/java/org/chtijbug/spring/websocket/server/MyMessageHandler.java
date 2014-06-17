package org.chtijbug.spring.websocket.server;

import org.chtijbug.spring.websocket.event.MyMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.DecodeException;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class MyMessageHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        //___ Check that the message contains a Json structure data.
        MyMessage.MyMessageStream stream = new MyMessage.MyMessageStream(MyMessage.class);
        session.sendMessage(message);
        try {
            MyMessage myMessage = stream.decode(new StringReader(message.getPayload()));
            myMessage.setTitle("Title from server");
            myMessage.setContent("Content from server");

            StringWriter writer = new StringWriter();
            stream.encode(myMessage, writer);
            TextMessage response = new TextMessage(writer.toString());
            System.out.println(">> Server : "+response);
            session.sendMessage(response);
        } catch (DecodeException | EncodeException e) {
            e.printStackTrace();
        }
    }

}