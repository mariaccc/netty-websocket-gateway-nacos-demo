package com.mariaccc.demo.websocket;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Properties;

/**
 * @author ccc
 * @version 1.0.0
 * created on 2021/8/13
 */
@Component
@Order(value = 1)
@Slf4j
public class WebsocketRegisterProvider implements CommandLineRunner {

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddr;
    @Value("${spring.cloud.nacos.discovery.namespace}")
    private String namespace;
    @Value("${mariaccc.websocket.port}")
    private int port;
    @Value("${mariaccc.websocket.name}")
    private String serviceName;

    @Override
    public void run(String... args) throws Exception {
        log.info("register netty-websocket to nacos");
        Properties properties = new Properties();
        properties.setProperty("serverAddr", serverAddr);
        properties.setProperty("namespace", namespace);
        NamingService namingService = NamingFactory.createNamingService(properties);
        namingService.registerInstance(serviceName, InetAddress.getLocalHost().getHostAddress(), port);
    }
}
