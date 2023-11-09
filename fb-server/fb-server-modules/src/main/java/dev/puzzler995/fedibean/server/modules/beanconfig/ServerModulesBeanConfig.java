package dev.puzzler995.fedibean.server.modules.beanconfig;

import dev.puzzler995.fedibean.data.modules.beanconfig.DataModulesBeanConfig;
import dev.puzzler995.fedibean.federation.modules.beanconfig.FederationModulesBeanConfig;
import dev.puzzler995.fedibean.server.handler.beanconfig.ServerHandlerBeanConfig;
import dev.puzzler995.fedibean.ui.modules.beanconfig.UiModulesBeanConfig;
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
