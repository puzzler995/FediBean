package dev.puzzler995.fedibean.beanconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {APHandlerConfig.class, APSpecConfig.class})
public class APModuleConfig {}
