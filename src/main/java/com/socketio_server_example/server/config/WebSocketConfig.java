package com.socketio_server_example.server.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketConfig {

    /**
     * 創建SocketIo的Server
     */
    @Bean
    public SocketIOServer getSocketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("localhost");
        config.setPort(8118);
        return new SocketIOServer(config);
    }
}
