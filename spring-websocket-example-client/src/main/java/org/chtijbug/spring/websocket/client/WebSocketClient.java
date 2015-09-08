package org.chtijbug.spring.websocket.client;

import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;
import org.glassfish.tyrus.client.auth.Credentials;

import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public class WebSocketClient {
    Session userSession = null;
    private MessageHandler messageHandler;

    public WebSocketClient(URI endpointURI) {
        try {
            ClientManager client = ClientManager.createClient();
            client.getProperties().put(ClientProperties.CREDENTIALS, new Credentials("admin", "admin"));
            client.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //final WebSocketClient clientEndPoint = new WebSocketClient(new URI("ws://localhost:8080/server/myHandler"));
          final WebSocketClient clientEndPoint = new WebSocketClient(new URI("ws://localhost:10080/drools-platform-ui/server/runtime"));
        //clientEndPoint.
        clientEndPoint.addMessageHandler(new MessageHandler() {
            @Override
            public void handleMessage(String message) {
                System.out.println("*********************************");
                System.out.println(message);
                System.out.println("*********************************");
            }
        });
        while (true) {
            clientEndPoint.sendMessage("Hello "+Math.random()+" !!");
            Thread.sleep(2000);
        }
    }

    /**
     * Callback hook for Connection open events.
     *
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {
        this.userSession = userSession;
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason      the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        this.userSession = null;
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a
     * client send a message.
     *
     * @param message The text message
     */
    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null)
            this.messageHandler.handleMessage(message);
    }

    /**
     * register message handler
     *
     * @param msgHandler
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    /**
     * Send a message.
     *
     * @param message
     */
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }

    /**
     * Message handler.
     *
     * @author Jiji_Sasidharan
     */
    public static interface MessageHandler {
        public void handleMessage(String message);
    }

}