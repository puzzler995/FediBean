package dev.puzzler995.fedibean.beanconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {DataQueueBeanConfig.class, DataGraphBeanConfig.class})
public class DataModulesBeanConfig {}
