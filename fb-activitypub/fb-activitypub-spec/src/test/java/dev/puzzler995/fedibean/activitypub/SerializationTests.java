package dev.puzzler995.fedibean.activitypub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.google.common.collect.ImmutableList;
import dev.puzzler995.fedibean.activitypub.spec.model.APObject;
import dev.puzzler995.fedibean.activitypub.spec.model.Activity;
import dev.puzzler995.fedibean.activitypub.spec.model.Collection;
import dev.puzzler995.fedibean.activitypub.spec.model.CompactedIri;
import dev.puzzler995.fedibean.activitypub.spec.model.JsonContext;
import dev.puzzler995.fedibean.activitypub.spec.model.Link;
import dev.puzzler995.fedibean.activitypub.spec.model.Resolvable;
import dev.puzzler995.fedibean.activitypub.spec.modules.APMapper;
import dev.puzzler995.fedibean.beanconfig.APSpecConfig;
import io.micrometer.core.instrument.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = APSpecConfig.class)
class SerializationTests {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired private APMapper mapper;

  private static Stream<Arguments> realWorldActivities() {
    return Stream.of(
        Arguments.of("akkoma_boost_note.json", "Announce"),
        Arguments.of("akkoma_create_note.json", "Create"),
        Arguments.of("akkoma_create_note_poll.json", "Create"),
        Arguments.of("akkoma_custom_emoji_react.json", "EmojiReact"),
        Arguments.of("akkoma_edit_profile.json", "Update"),
        Arguments.of("akkoma_emoji_react.json", "EmojiReact"),
        Arguments.of("akkoma_like_note.json", "Like"),
        Arguments.of("akkoma_post_image.json", "Create"),
        Arguments.of("akkoma_quote_boost_cw.json", "Create"),
        Arguments.of("firefish_create_note.json", "Create"),
        Arguments.of("firefish_create_reply.json", "Create"),
        Arguments.of("firefish_edit_profile.json", "Update"),
        Arguments.of("firefish_emoji_react.json", "Like"),
        Arguments.of("firefish_like_note.json", "Like"),
        Arguments.of("firefish_reply_edit.json", "Update"),
        Arguments.of("firefish_unlike_note.json", "Undo"),
        Arguments.of("mastodon_create_note.json", "Create"),
        Arguments.of("mastodon_create_remote_note.json", "Create"),
        Arguments.of("mastodon_delete_note.json", "Delete"),
        Arguments.of("mastodon_follow.json", "Follow"),
        Arguments.of("mastodon_like.json", "Like"),
        Arguments.of("mastodon_undo_like.json", "Undo"),
        Arguments.of("mastodon_update_note.json", "Update"));
  }

  private static String readResource(String name) throws IOException {
    ClassLoader classLoader = DeserializationTests.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(name);
    String jsonContent = "";
    if (inputStream != null) {
      try {
        jsonContent = IOUtils.toString(inputStream);
      } finally {
        inputStream.close();
      }
    }
    return jsonContent;
  }

  private static Stream<Integer> fileNumbersProvider() {
    return IntStream.range(0, 159).boxed();
  }

  @Test
  void serializeCollection() throws JsonProcessingException {
    Collection collection =
        new Collection() {
          {
            this.setType("Collection");
            this.setId(new CompactedIri("https://fedibean.example/not/1/replies"));
            this.setItems(
                new ArrayList<>(
                    ImmutableList.of(new Link("https://mastodon.example/user/someguy/101"))));
          }
        };

    String actual = mapper.serialize(collection);
    //    String actual = objectMapper.writeValueAsString(collection);
    assertNotNull(actual);
  }

  @Test
  void seralizeList() throws JsonProcessingException {
    APObject obj =
        new APObject() {
          {
            this.setType("Note");
            this.setAttachment(
                new ArrayList<>(
                    ImmutableList.of(
                        new APObject() {
                          {
                            this.setType("Image");
                          }
                        },
                        new APObject() {
                          {
                            this.setType("Image");
                          }
                        },
                        new APObject() {
                          {
                            this.setType("Image");
                          }
                        })));
          }
        };
    JsonNode actual = objectMapper.valueToTree(obj);
    assertEquals(3, actual.get("attachment").size());
    assertEquals("Image", actual.get("attachment").get(0).get("type").get(0).asText());
  }

  @Test
  void serializeMediaType() {
    APObject obj =
        new APObject() {
          {
            this.setType("Image");
            this.setMediaType("image/jpeg");
          }
        };
    JsonNode actual = objectMapper.valueToTree(obj);
    assertEquals("image/jpeg", actual.get("mediaType").asText());
  }

  @Test
  void serializeSimpleObject() {
    APObject obj =
        new APObject() {
          {
            this.setType("Object");
            this.setContent("test content");
            this.setId(new CompactedIri("https://fedibean.example/1"));
          }
        };
    JsonNode actual = objectMapper.valueToTree(obj);
    assertEquals("test content", actual.get("content").asText());
    // TODO: assertEquals("https://fedibean.example/1", actual);???
  }

  @Test
  void excludesNull() {
    APObject obj =
        new APObject() {
          {
            this.setContent("test content");
            this.setId(new CompactedIri("https://fedibean.example/1"));
            this.setType("Note");
          }
        };
    JsonNode actual = objectMapper.valueToTree(obj);
    assertNull(actual.get("bto"));
    assertNull(actual.get("null"));
    assertEquals("test content", actual.get("content").asText());
    // TODO: assertEquals("test content, actual);???
  }

  @Test
  void serializeJsonContext() throws JSONException {
    APObject obj =
        new APObject() {
          {
            this.setContent("test content");
            this.setId(new CompactedIri("https://fedibean.example/1"));
            this.setType("Note");
          }
        };
    obj.addJsonContext(JsonContext.activityStreams);
    obj.addJsonContext(new JsonContext("toot", "https://mastodon.example/schema#"));
    JsonNode actual = objectMapper.valueToTree(obj);
    JsonNode context = actual.get("@context");
    JSONAssert.assertEquals(
        "[\"https://www.w3.org/ns/activitystreams\",\"toot:https://mastodon.example/schema#\"]",
        String.valueOf(context),
        false);
  }

  @Test
  void serializeSingleJsonContext() throws JsonProcessingException {
    APObject obj =
        new APObject() {
          {
            this.setContent("test content");
            this.setId(new CompactedIri("https://fedibean.example/1"));
            this.setType("Note");
          }
        };
    obj.addJsonContext(JsonContext.activityStreams);
    String actual = objectMapper.writeValueAsString(obj);
    assertNotNull(actual);
    assertTrue(actual.contains("\"@context\":[\"https://www.w3.org/ns/activitystreams\"]"));
  }

  @Test
  void serializeSimpleActivity() throws JsonProcessingException {
    Activity testActivity =
        new Activity() {
          {
            this.setContent("test content");
            this.setId(new CompactedIri("https://fedibean.example/1"));
            this.setType("Like");
          }
        };
    APObject testTarget =
        new APObject() {
          {
            this.setId(new CompactedIri("https://mastodon.example/2"));
            this.setType("Note");
          }
        };
    testActivity.addTarget(testTarget);
    String actual = objectMapper.writeValueAsString(testActivity);
    assertNotNull(actual);
    assertFalse(actual.contains("@context"));
  }

  @Test
  void serializeLinksAsString_WhenOnlyHrefIsSet() throws JsonProcessingException {
    Link link = new Link("https://example.com");
    String actual = objectMapper.writeValueAsString(link);
    assertEquals("\"https://example.com\"", actual);
  }

  @Test
  void serializeLinksAsObject_WhenPropertiesAreSet() {
    Link link =
        new Link("https://example.com") {
          {
            this.setRel(Collections.singletonList("me"));
          }
        };
    JsonNode actual = objectMapper.valueToTree(link);
    assertEquals(JsonNodeType.OBJECT, actual.getNodeType());
    assertTrue(actual.has("href"));
    assertEquals("https://example.com", actual.get("href").asText());
    assertTrue(actual.has("rel"));
    assertEquals("me", actual.get("rel").asText());
  }

  @ParameterizedTest
  @MethodSource({"realWorldActivities"})
  void realWorldTestActivity(String filename) throws IOException {
    String json = readResource(filename);
    Resolvable actualRes = mapper.deserialize(json);
    JsonNode expected = objectMapper.readTree(json);
    String actualString = mapper.serialize(actualRes);
    JsonNode actual = objectMapper.readTree(actualString);
  }
}
