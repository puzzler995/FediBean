package dev.puzzler995.fedibean.activitypub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import dev.puzzler995.fedibean.activitypub.spec.model.APObject;
import dev.puzzler995.fedibean.activitypub.spec.model.Activity;
import dev.puzzler995.fedibean.activitypub.spec.model.Actor;
import dev.puzzler995.fedibean.activitypub.spec.model.Resolvable;
import dev.puzzler995.fedibean.activitypub.spec.modules.APMapper;
import dev.puzzler995.fedibean.beanconfig.APSpecConfig;
import io.micrometer.core.instrument.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = APSpecConfig.class)
class DeserializationTests {
  @Autowired private APMapper mapper;

  private static Stream<Integer> fileNumbersProvider() {
    return IntStream.range(0, 159).boxed();
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

  @ParameterizedTest
  @MethodSource("fileNumbersProvider")
  void activityStreamDeserializationTest(int fileNumber) throws IOException {
    String json = readResource("ASExamples/example_" + fileNumber + ".json");
    Resolvable actual = mapper.deserialize(json);
    assertNotNull(actual);
  }

  @Test
  void canReadMediaType() {
    var json =
        """
{
  "type": "Image",
  "mediaType": "image/jpeg",
  "url": "https://cdn.mastodon.example/test_actor/accounts/avatars/109/497/783/827/254/564/original/b0adb5063df194a6.jpg"
}
  """;
    Resolvable resolvable = mapper.deserialize(json);
    assertTrue(resolvable instanceof APObject);
    APObject actual = (APObject) resolvable;
    assertNotNull(actual);
  }

  @ParameterizedTest
  @MethodSource("realWorldActivities")
  void canReadRealWorldActivities(String activity, String expected) throws IOException {
    String json = readResource(activity);
    Resolvable actual = mapper.deserialize(json);
    assertEquals(expected, actual.getType().get(0));
  }

  @Test
  void canReadRealWorldActor() throws IOException {
    String json = readResource("mastodon_actor.json");
    Resolvable actualRes = mapper.deserialize(json);
    assertTrue(actualRes instanceof Actor);
    Actor actual = (Actor) actualRes;
    assertNotNull(actual);
    assertEquals(
        "https://mastodon.example/users/test_actor/following",
        actual.getFollowing().getId().toString());
    assertEquals(
        "https://mastodon.example/users/test_actor/followers",
        actual.getFollowers().getId().toString());
    assertEquals(
        "https://mastodon.example/users/test_actor/inbox", actual.getInbox().getId().toString());
    assertEquals(
        "https://mastodon.example/users/test_actor/outbox", actual.getOutbox().getId().toString());
  }

  @Test
  void canReadRealWorldNote() throws IOException {
    String json = readResource("mastodon_create_note.json");
    Resolvable actualRes = mapper.deserialize(json);
    assertTrue(actualRes instanceof Activity);
    Activity actual = (Activity) actualRes;
    if (actual.getObject() != null && !actual.getObject().isEmpty()) {
      APObject note = (APObject) actual.getObject().get(0);
      assertEquals("<p>Creating Test Data!!! :)</p>", note.getContent());
      assertTrue(note.getContentMap().getLanguages().contains("en"));
      assertEquals("<p>Creating Test Data!!! :)</p>", note.getContentMap().get("en"));
      assertEquals("Creating Test Data!!! :)", note.getSource().getContent());
    } else {
      fail("Not a note");
    }
  }
}
