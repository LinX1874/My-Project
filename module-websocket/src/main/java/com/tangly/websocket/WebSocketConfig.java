package com.tangly.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author tangly
 */
@Configuration
@Slf4j
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

//    @Bean
//    public ServletContextAware endpointExporterInitializer(final ApplicationContext applicationContext) {
//        return servletContext -> {
//            ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
//            serverEndpointExporter.setApplicationContext(applicationContext);
//            try {
//                serverEndpointExporter.afterPropertiesSet();
//            } catch (Exception e) {
//                //单元测试时没有WebSocket环境 ， 不影响其他模块测试
//                log.debug("WebSocket 环境没有启动",e);
//            }
//        };
//    }

}
