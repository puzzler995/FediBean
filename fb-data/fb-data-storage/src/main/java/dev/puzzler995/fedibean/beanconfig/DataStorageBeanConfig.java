package dev.puzzler995.fedibean.beanconfig;

import dev.puzzler995.fedibean.data.storage.model.DataStorageModel;
import dev.puzzler995.fedibean.data.storage.modules.DataStorageModules;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("dev.puzzler995.fedibean.data.storage.modules.repository")
@ComponentScan(basePackageClasses = {DataStorageModules.class})
@EntityScan(basePackageClasses = {DataStorageModel.class})
public class DataStorageBeanConfig {}
