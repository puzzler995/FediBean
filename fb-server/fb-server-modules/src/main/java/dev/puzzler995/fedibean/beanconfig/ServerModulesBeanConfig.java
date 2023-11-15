package dev.puzzler995.fedibean.beanconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(
    value = {
      ServerHandlerBeanConfig.class,
      UiModulesBeanConfig.class,
      FederationModulesBeanConfig.class,
      DataModulesBeanConfig.class
    })
public class ServerModulesBeanConfig {}
