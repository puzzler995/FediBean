package dev.puzzler995.fedibean.data;

import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.neo4j.driver.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;

@Configuration
public class DataBeanConfig {
  @Bean
  public Neo4jTransactionManager transactionManager(
      Driver driver, DatabaseSelectionProvider databaseNameProvider) {
    return new Neo4jTransactionManager(driver, databaseNameProvider);
  }

  @Bean
  org.neo4j.cypherdsl.core.renderer.Configuration cypherDslConfiguration() {
    return org.neo4j.cypherdsl.core.renderer.Configuration.newConfig()
        .withDialect(Dialect.NEO4J_5)
        .build();
  }
}
