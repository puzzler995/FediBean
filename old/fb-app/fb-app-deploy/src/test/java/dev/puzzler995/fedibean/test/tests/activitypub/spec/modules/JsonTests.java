package dev.puzzler995.fedibean.test.tests.activitypub.spec.modules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.balajeetm.mystique.util.gson.lever.JsonLever;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import dev.puzzler995.fedibean.activitypub.spec.model.APObject;
import dev.puzzler995.fedibean.activitypub.spec.model.Activity;
import dev.puzzler995.fedibean.activitypub.spec.model.Actor;
import dev.puzzler995.fedibean.activitypub.spec.model.Link;
import dev.puzzler995.fedibean.activitypub.spec.model.Resolvable;
import dev.puzzler995.fedibean.test.app.application.TestFedibeanApplication;
import io.micrometer.core.instrument.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

@SpringBootTest(classes = {TestFedibeanApplication.class})
@AutoConfigureJsonTesters
@AutoConfigureJson
class JsonTests {

  @Autowired private JacksonTester<Resolvable> jacksonTester;
  @Autowired private JsonTestParser jsonTestParser;
  @Autowired private JsonLever jsonLever;
  @Autowired private ObjectMapper objectMapper;

  private static Stream<Integer> fileNumbersProvider() {
    return IntStream.range(0, 159).boxed();
  }

  private static String readResource(String name) throws IOException {
    ClassLoader classLoader = JsonTests.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(name);
    String jsonContent = "";
    if (inputStream != null) {
      try {
        jsonContent = IOUtils.toString(inputStream);
      } finally {
        inputStream.close();
      }
    }
    Object uncompact = JsonUtils.fromString(jsonContent);
    Object compact = JsonLdProcessor.compact(uncompact, new HashMap<>(), new JsonLdOptions());
    jsonContent = JsonUtils.toPrettyString(compact);
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
        Arguments.of("firefish_create_poll.json", "Create"),
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
  void activityStreamDeserializationTest(int fileNumber) {
    try {
      String json = readResource("ASExamples/example_" + fileNumber + ".json");
      Resolvable actualR = this.jacksonTester.parseObject(json);
      assertThat(actualR).isNotNull();
      String actualWrite = this.objectMapper.writeValueAsString(actualR);
      JSONAssert.assertEquals(json, actualWrite, false);
    } catch (IOException | JSONException e) {
      fail("Failed due to exception: ", e);
    }
  }

  @Test
  void canReadMediaType() {
    try {
      String content =
          "{\"@context\": \"https://www.w3.org/ns/activitystreams\", \"type\": \"Image\",\"mediaType\": \"image/jpeg\",\"url\": \"https://cdn.mastodon.example/test_actor/accounts/avatars/109/497/783/827/254/564/original/b0adb5063df194a6.jpg\"}";
      Object uncompact = JsonUtils.fromString(content);
      Object compact = JsonLdProcessor.compact(uncompact, new HashMap<>(), new JsonLdOptions());
      content = JsonUtils.toPrettyString(compact);
      APObject expected = new APObject();
      expected.setType("https://www.w3.org/ns/activitystreams#Image");
      expected.setMediaType("image/jpeg");
      expected.setUrl(
          Collections.singletonList(
              new Link(
                  "https://cdn.mastodon.example/test_actor/accounts/avatars/109/497/783/827/254/564/original/b0adb5063df194a6.jpg")));
      Resolvable actualR = this.jacksonTester.parseObject(content);
      assertThat(actualR).isInstanceOf(APObject.class);
      APObject actual = (APObject) actualR;
      assertThat(this.jacksonTester.parse(content)).isEqualTo(expected);
      assertThat(actual.getType()).contains("https://www.w3.org/ns/activitystreams#Image");
      assertThat(actual.getMediaType()).isEqualTo("image/jpeg");
      Resolvable actualL = actual.getUrl().get(0);
      assertThat(actualL).isInstanceOf(Link.class);
      Link actualLink = (Link) actualL;
      assertThat(actualLink.getHref())
          .hasToString(
              "https://cdn.mastodon.example/test_actor/accounts/avatars/109/497/783/827/254/564/original/b0adb5063df194a6.jpg");

      String actualWrite = this.objectMapper.writeValueAsString(actual);
      JSONAssert.assertEquals(content, actualWrite, false);
    } catch (IOException | JSONException e) {
      fail("Failed due to exception: ", e);
    }
  }

  @ParameterizedTest
  @MethodSource("realWorldActivities")
  void canReadRealWorldActivities(String activityFile, String expected) {
    try {
      String json = readResource(activityFile);
      Resolvable actualR = this.jacksonTester.parseObject(json);
      assertThat(actualR).isInstanceOf(Activity.class);
      Activity actual = (Activity) actualR;
      assertThat(actual.getType()).contains(expected);
      String actualWrite = this.objectMapper.writeValueAsString(actual);
      JSONAssert.assertEquals(json, actualWrite, false);
      // jsonTestParser.parseJsonTest(this.objectMapper.readTree(json), actual); TODO: Fix this
    } catch (IOException | JSONException e) {
      fail("Failed due to exception: ", e);
    }
  }

  @Test
  void canReadRealWorldActor() {
    try {
      String json = readResource("mastodon_actor.json");
      Resolvable actualR = this.jacksonTester.parseObject(json);
      assertThat(actualR).isInstanceOf(Actor.class);
      Actor actual = (Actor) actualR;
      assertThat(actual).isNotNull();
      assertThat(actual.getFollowing().getId())
          .hasToString("https://mastodon.example/users/test_actor/following");
      assertThat(actual.getFollowers().getId())
          .hasToString("https://mastodon.example/users/test_actor/followers");
      assertThat(actual.getInbox().getId())
          .hasToString("https://mastodon.example/users/test_actor/inbox");
      assertThat(actual.getOutbox().getId())
          .hasToString("https://mastodon.example/users/test_actor/outbox");
      String actualWrite = this.objectMapper.writeValueAsString(actual);
      JSONAssert.assertEquals(json, actualWrite, false);
    } catch (IOException | JSONException e) {
      fail("Failed due to exception: ", e);
    }
  }
}
