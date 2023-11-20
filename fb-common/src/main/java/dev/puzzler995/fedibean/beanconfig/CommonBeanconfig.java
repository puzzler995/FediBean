package dev.puzzler995.fedibean.beanconfig;

import dev.puzzler995.fedibean.common.modules.CommonModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {CommonModules.class})
public class CommonBeanconfig {}
