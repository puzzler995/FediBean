package dev.puzzler995.fedibean.beanconfig;

import dev.puzzler995.fedibean.data.graph.model.DataGraphModel;
import dev.puzzler995.fedibean.data.graph.modules.DataGraphModules;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackageClasses = {DataGraphModules.class})
@ComponentScan(basePackageClasses = {DataGraphModules.class})
@EntityScan(basePackageClasses = {DataGraphModel.class})
@EnableTransactionManagement
public class DataGraphBeanConfig {

  @Bean
  org.neo4j.cypherdsl.core.renderer.Configuration cypherDslConfiguration() {
    return org.neo4j.cypherdsl.core.renderer.Configuration.newConfig()
        .withDialect(Dialect.NEO4J_5)
        .build();
  }
}
