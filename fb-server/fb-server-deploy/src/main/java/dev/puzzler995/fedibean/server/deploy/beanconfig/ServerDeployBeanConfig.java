package dev.puzzler995.fedibean.server.deploy.beanconfig;

import dev.puzzler995.fedibean.server.modules.beanconfig.ServerModulesBeanConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {ServerModulesBeanConfig.class})
public class ServerDeployBeanConfig {}
