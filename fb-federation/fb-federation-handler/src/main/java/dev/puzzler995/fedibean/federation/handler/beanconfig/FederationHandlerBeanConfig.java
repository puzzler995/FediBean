package dev.puzzler995.fedibean.federation.handler.beanconfig;

import dev.puzzler995.fedibean.federation.handler.modules.FederationHandlerModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {FederationHandlerModules.class})
public class FederationHandlerBeanConfig {}
