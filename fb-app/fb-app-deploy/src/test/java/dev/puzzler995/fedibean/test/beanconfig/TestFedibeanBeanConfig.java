package dev.puzzler995.fedibean.test.beanconfig;

import dev.puzzler995.fedibean.beanconfig.BaseBeanConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {BaseBeanConfig.class, TestBeanConfig.class})
public class TestFedibeanBeanConfig {}
