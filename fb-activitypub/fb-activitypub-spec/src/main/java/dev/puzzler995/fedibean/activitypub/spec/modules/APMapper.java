package dev.puzzler995.fedibean.activitypub.spec.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.puzzler995.fedibean.activitypub.spec.model.Resolvable;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
@AllArgsConstructor
@NoArgsConstructor
public class APMapper {

  @Getter @Autowired private ObjectMapper mapper;

  @PostConstruct
  private void testi() {
    System.out.println("Making mapper");
  }

  public Resolvable deserialize(String json) {
    try {
      return mapper.readValue(json, Resolvable.class);
    } catch (Exception e) {
      throw new RuntimeException(e); // TODO: Exception handling
    }
  }

  public String serialize(Resolvable resolvable) {
    try {
      return mapper.writeValueAsString(resolvable);
    } catch (Exception e) {
      throw new RuntimeException(e); // TODO: Exception handling
    }
  }
}
