package dev.puzzler995.fedibean.beanconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {DataStorageConfig.class})
// @ComponentScan(basePackageClasses = {DataStorageConfig.class, DataQueueConfig.class})
public class DataModuleConfig {}
