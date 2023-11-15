package dev.puzzler995.fedibean.beanconfig;

import dev.puzzler995.fedibean.data.graph.modules.DataGraphModules;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {DataGraphModules.class})
public class DataGraphBeanConfig {

  @Bean
  org.neo4j.cypherdsl.core.renderer.Configuration cypherDslConfiguration() {
    return org.neo4j.cypherdsl.core.renderer.Configuration.newConfig()
        .withDialect(Dialect.NEO4J_5)
        .build();
  }
}
