package dev.puzzler995.fedibean.data.storage.beanconfig;

import dev.puzzler995.fedibean.data.storage.modules.DataStorageModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {DataStorageModules.class})
public class DataStorageBeanConfig {}
