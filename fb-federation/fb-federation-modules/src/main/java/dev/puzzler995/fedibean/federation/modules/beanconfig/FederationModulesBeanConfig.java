package dev.puzzler995.fedibean.federation.modules.beanconfig;

import dev.puzzler995.fedibean.federation.handler.beanconfig.FederationHandlerBeanConfig;
import dev.puzzler995.fedibean.federation.spec.beanconfig.FederationSpecBeanConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {FederationSpecBeanConfig.class, FederationHandlerBeanConfig.class})
public class FederationModulesBeanConfig {}
