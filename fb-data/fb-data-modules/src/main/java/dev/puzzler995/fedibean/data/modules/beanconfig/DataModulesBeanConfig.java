package dev.puzzler995.fedibean.data.modules.beanconfig;

import dev.puzzler995.fedibean.data.queue.beanconfig.DataQueueBeanConfig;
import dev.puzzler995.fedibean.data.storage.beanconfig.DataStorageBeanConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {DataQueueBeanConfig.class, DataStorageBeanConfig.class})
public class DataModulesBeanConfig {}
