package com.nilesh.servlets;
import com.nilesh.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatServlet {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        String username = (String) session.getUserProperties().get("username");

        // Get the current timestamp
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        saveMessageToDatabase(username, message, timestamp);

        // Broadcast the message to all connected clients
        for (Session s : sessions) {
            s.getBasicRemote().sendText("[" + timestamp.toString() + "] " +username + ": " + message);
        }
    }

    private void saveMessageToDatabase(String username, String message, Timestamp timestamp) {
       

        try (Connection connection = DatabaseConnection.getConnection(); ) {
            String query = "INSERT INTO messages (name, content, timestamp) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, message);
                statement.setTimestamp(3, timestamp);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
