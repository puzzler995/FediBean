package dev.puzzler995.fedibean.beanconfig;

import dev.puzzler995.fedibean.data.modules.DataStorageModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {DataStorageModules.class})
public class DataStorageConfig {}
