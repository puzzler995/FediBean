package dev.puzzler995.fedibean.server.handler.beanconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ServerHandlerBeanConfig.class})
public class ServerHandlerBeanConfig {}
