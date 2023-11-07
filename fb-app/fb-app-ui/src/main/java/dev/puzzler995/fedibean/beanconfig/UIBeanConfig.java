package dev.puzzler995.fedibean.beanconfig;

import dev.puzzler995.fedibean.app.UiModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {UiModules.class})
public class UIBeanConfig {}
