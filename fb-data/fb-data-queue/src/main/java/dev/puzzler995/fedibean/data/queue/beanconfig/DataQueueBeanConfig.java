package dev.puzzler995.fedibean.data.queue.beanconfig;

import dev.puzzler995.fedibean.data.queue.modules.DataQueueModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {DataQueueModules.class})
public class DataQueueBeanConfig {}
