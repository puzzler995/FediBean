package dev.puzzler995.fedibean.beanconfig;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.puzzler995.fedibean.activitypub.spec.model.Resolvable;
import dev.puzzler995.fedibean.activitypub.spec.modules.ActivityPubSpecModules;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer.OffsetDateTimeDeserializer;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer.ResolvableDeserializer;
import java.time.OffsetDateTime;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ActivityPubSpecModules.class)
public class APSpecConfig {
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jacksonConfig() {
    SimpleModule deserializers = new SimpleModule();
    deserializers.addDeserializer(Resolvable.class, new ResolvableDeserializer());
    deserializers.addDeserializer(OffsetDateTime.class, new OffsetDateTimeDeserializer());
    return builder ->
        builder
            .modules(new JavaTimeModule(), deserializers)
            .featuresToEnable(
                DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
                SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)
     .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }
}
