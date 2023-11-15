package dev.puzzler995.fedibean.beanconfig;

import dev.puzzler995.fedibean.ui.app.frontend.UiAppFrontend;
import dev.puzzler995.fedibean.ui.app.modules.UiAppModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {UiAppFrontend.class, UiAppModules.class})
public class UiAppBeanConfig {}
