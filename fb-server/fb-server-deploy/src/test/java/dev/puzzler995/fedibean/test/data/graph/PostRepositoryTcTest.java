package dev.puzzler995.fedibean.test.data.graph;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import dev.puzzler995.fedibean.server.deploy.app.FediBeanApplication;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;

@DataNeo4jTest
@ContextConfiguration(classes = {FediBeanApplication.class})
class PostRepositoryTcTest {
  private static Neo4jContainer<?> neo4jContainer;

  @BeforeAll
  static void initializeNeo4j() {
    neo4jContainer = new Neo4jContainer<>("neo4j:5.13.0").withAdminPassword("12345678");
    neo4jContainer.start();
  }

  @DynamicPropertySource
  static void neo4jProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
    registry.add("spring.neo4j.authentication.username", () -> "neo4j");
    registry.add("spring.neo4j.authentication.password", neo4jContainer::getAdminPassword);
  }

  @AfterAll
  static void stopNeo4j() {
    neo4jContainer.close();
  }

  @Test
  void testFindSomethingShouldWork(@Autowired Neo4jClient client) {
    Optional<Long> result = client.query("MATCH (n) RETURN COUNT(n)").fetchAs(Long.class).one();
    assertThat(result).hasValue(0L);
  }
}
