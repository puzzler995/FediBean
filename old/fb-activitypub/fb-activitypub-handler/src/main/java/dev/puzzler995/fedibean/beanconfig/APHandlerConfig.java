package dev.puzzler995.fedibean.beanconfig;

import dev.puzzler995.fedibean.activitypub.handler.modules.ActivityPubHandlerModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ActivityPubHandlerModules.class})
public class APHandlerConfig {}
