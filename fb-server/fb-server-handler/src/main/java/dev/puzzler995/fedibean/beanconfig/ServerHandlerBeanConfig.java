package dev.puzzler995.fedibean.beanconfig;

import dev.puzzler995.fedibean.server.handler.modules.ServerHandlerModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ServerHandlerModules.class})
public class ServerHandlerBeanConfig {}
