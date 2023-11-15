package dev.puzzler995.fedibean.beanconfig;

import dev.puzzler995.fedibean.federation.spec.modules.FederationSpecModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {FederationSpecModules.class})
public class FederationSpecBeanConfig {}
