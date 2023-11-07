package dev.puzzler995.fedibean.activitypub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.puzzler995.fedibean.activitypub.spec.model.APObject;
import dev.puzzler995.fedibean.activitypub.spec.model.JsonContext;
import dev.puzzler995.fedibean.activitypub.spec.model.Resolvable;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer.ResolvableDeserializer;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer.JsonContextListSerializer;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ContextTests {
  protected static final ObjectMapper objectMapper = new ObjectMapper();

  static {
    SimpleModule module = new SimpleModule();
    module.addDeserializer(Resolvable.class, new ResolvableDeserializer());
    module.addSerializer(new JsonContextListSerializer());
    objectMapper.registerModule(module);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Nested
  class ReadTests {

    @Test
    void flattenArrayOfObjects() {
      String inputJson =
          """
{
  "@context": [
    {
      "@import": "https://www.w3.org/ns/activitystreams"
    },
    {
      "term1": "definition1",
      "term2": "definition2"
    }
  ],
    "type": "Object"
}
""";
      APObject object = null;
      try {
        object = objectMapper.readValue(inputJson, APObject.class);
      } catch (JsonProcessingException e) {
        fail(e);
      }
      //List<JsonContext> contexts = object.getJsonContext();
      //assertNotNull(contexts);
      //assertEquals(3, contexts.size());
      //assertEquals("@import", contexts.get(0).getPrefix());
      //assertEquals("https://www.w3.org/ns/activitystreams", contexts.get(0).getSuffix());
      //assertEquals("term1", contexts.get(1).getPrefix());
      //assertEquals("definition1", contexts.get(1).getSuffix());
      //assertEquals("term2", contexts.get(2).getPrefix());
      //assertEquals("definition2", contexts.get(2).getSuffix());
    }

    @Test
    void mergeArrayOfMixedTypes() {
      String inputJson =
          """
{
  "@context": [
    "https://www.w3.org/ns/activitystreams",
    {
      "term1": "definition1",
      "term2": "definition2"
    }
  ],
    "type": "Object"
}
""";
      APObject object = null;
      try {
        object = objectMapper.readValue(inputJson, APObject.class);
      } catch (JsonProcessingException e) {
        fail(e);
      }
      //List<JsonContext> contexts = object.getJsonContext();
      //assertNotNull(contexts);
      //assertEquals(3, contexts.size());
      //assertNull(contexts.get(0).getPrefix());
      //assertEquals("https://www.w3.org/ns/activitystreams", contexts.get(0).getSuffix());
      //assertEquals("term1", contexts.get(1).getPrefix());
      //assertEquals("definition1", contexts.get(1).getSuffix());
      //assertEquals("term2", contexts.get(2).getPrefix());
      //assertEquals("definition2", contexts.get(2).getSuffix());
    }

    @Test
    void readArrayOfStringsAsMultipleSuffixes() {
      String inputJson =
          """
{
  "@context": [
    "https://www.w3.org/ns/activitystreams",
    "https://example.com"
  ],
  "type": "Object"
}
""";
      APObject object = null;
      try {
        object = objectMapper.readValue(inputJson, APObject.class);
      } catch (JsonProcessingException e) {
        fail(e);
      }
      //List<JsonContext> contexts = object.getJsonContext();
      //assertNotNull(contexts);
      //assertEquals(2, contexts.size());
      //assertNull(contexts.get(0).getPrefix());
      //assertEquals("https://www.w3.org/ns/activitystreams", contexts.get(0).getSuffix());
      //assertNull(contexts.get(1).getPrefix());
      //assertEquals("https://example.com", contexts.get(1).getSuffix());
    }

    @Test
    void readObjectAsPairs() {
      String inputJson =
          """
{
  "@context": {
    "@import": "https://www.w3.org/ns/activitystreams",
    "term1": "definition1",
    "term2": "definition2"
  },
    "type": "Object"
}
""";
      APObject object = null;
      try {
        object = objectMapper.readValue(inputJson, APObject.class);
      } catch (JsonProcessingException e) {
        fail(e);
      }
      //List<JsonContext> contexts = object.getJsonContext();
      //assertNotNull(contexts);
      //assertEquals(3, contexts.size());
      //assertEquals("@import", contexts.get(0).getPrefix());
      //assertEquals("https://www.w3.org/ns/activitystreams", contexts.get(0).getSuffix());
      //assertEquals("term1", contexts.get(1).getPrefix());
      //assertEquals("definition1", contexts.get(1).getSuffix());
      //assertEquals("term2", contexts.get(2).getPrefix());
      //assertEquals("definition2", contexts.get(2).getSuffix());
    }

    @Test
    void readStringAsSingleSuffix() {
      String inputJson =
          """
{
  "@context":"https://www.w3.org/ns/activitystreams",
  "type":"Object"
}
""";
      try {
        APObject object = objectMapper.readValue(inputJson, APObject.class);
        //List<JsonContext> contexts = object.getJsonContext();
        //assertNotNull(contexts);
        //for (JsonContext context : contexts) {
        //  assertNotNull(context);
        //  assertNull(context.getPrefix());
        //  assertEquals("https://www.w3.org/ns/activitystreams", context.getSuffix());
        //}
      } catch (JsonProcessingException e) {
        fail(e);
      }
    }
  }

  @Nested
  class WriteTests {
    @Test
    void writeMixedAsArray() {
      List<JsonContext> contexts =
          List.of(
              new JsonContext("https://www.w3.org/ns/activitystreams"),
              new JsonContext("term1", "definition1"));

      JsonNode output = objectMapper.valueToTree(contexts);
      assertTrue(output.isArray());
      assertTrue(output.get(0).isTextual());
      assertEquals("https://www.w3.org/ns/activitystreams", output.get(0).asText());
      assertTrue(output.get(1).isObject());
      assertEquals("definition1", output.get(1).get("term1").asText());
    }

    @Test
    void writeMultiplePairAsObject() {
      List<JsonContext> contexts =
          List.of(new JsonContext("term1", "definition1"), new JsonContext("term2", "definition2"));

      JsonNode output = objectMapper.valueToTree(contexts);
      assertTrue(output.isObject());
      assertEquals("definition1", output.get("term1").asText());
      assertEquals("definition2", output.get("term2").asText());
    }

    @Test
    void writeMultipleStringsAsArray() {
      List<JsonContext> contexts =
          List.of(
              new JsonContext("https://www.w3.org/ns/activitystreams"),
              new JsonContext("https://example.com"));

      JsonNode output = objectMapper.valueToTree(contexts);
      assertTrue(output.isArray());
      assertTrue(output.get(0).isTextual());
      assertEquals("https://www.w3.org/ns/activitystreams", output.get(0).asText());
      assertTrue(output.get(1).isTextual());
      assertEquals("https://example.com", output.get(1).asText());
    }

    @Test
    void writeSinglePairAsObject() {
      List<JsonContext> contexts = List.of(new JsonContext("term1", "definition1"));

      JsonNode output = objectMapper.valueToTree(contexts);
      assertTrue(output.isObject());
      assertEquals("definition1", output.get("term1").asText());
    }

    @Test
    void writeSingleStringAsString() {
      List<JsonContext> contexts =
          List.of(new JsonContext("https://www.w3.org/ns/activitystreams"));

      JsonNode output = objectMapper.valueToTree(contexts);
      assertTrue(output.isTextual());
      assertEquals("https://www.w3.org/ns/activitystreams", output.asText());
    }
  }
}
