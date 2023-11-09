package dev.puzzler995.fedibean.ui.modules.beanconfig;

import dev.puzzler995.fedibean.ui.app.beanconfig.UiAppBeanConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {UiAppBeanConfig.class})
public class UiModulesBeanConfig {}
