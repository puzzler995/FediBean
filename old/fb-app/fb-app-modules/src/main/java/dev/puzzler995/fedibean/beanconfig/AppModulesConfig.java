package dev.puzzler995.fedibean.beanconfig;

import dev.puzzler995.fedibean.app.modules.AppModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {AppModules.class})
public class AppModulesConfig {}
