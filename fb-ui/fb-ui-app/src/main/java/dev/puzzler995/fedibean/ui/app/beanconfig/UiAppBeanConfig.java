package dev.puzzler995.fedibean.ui.app.beanconfig;

import dev.puzzler995.fedibean.ui.app.frontend.UiAppModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {UiAppModules.class})
public class UiAppBeanConfig {}
