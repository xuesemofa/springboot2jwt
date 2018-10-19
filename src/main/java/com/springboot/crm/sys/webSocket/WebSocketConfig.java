package com.springboot.crm.sys.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author ld
 * @name
 * @table
 * @remarks 如果使用独立的servlet容器，而不是直接使用springboot的内置容器，
 * 就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理。
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
