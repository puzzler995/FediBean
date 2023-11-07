package dev.puzzler995.fedibean.beanconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(
    value = {
      APModuleConfig.class,
      DataModuleConfig.class,
      AppModulesConfig.class,
      UIBeanConfig.class
    })
public class BaseBeanConfig {}
