package dev.puzzler995.fedibean.test.beanconfig;

import dev.puzzler995.fedibean.test.tests.activitypub.spec.modules.ActivityPubTestModules;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ActivityPubTestModules.class})
public class TestBeanConfig {}
