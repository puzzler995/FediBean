package dev.puzzler995.fedibean.activitypub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.puzzler995.fedibean.activitypub.spec.model.CompactedIri;
import dev.puzzler995.fedibean.activitypub.spec.model.CompactedIri.TryCreateResults;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class CompactedIriTest {
  private static Stream<Arguments> compactStrings() {
    return Stream.of(
        Arguments.of("as:activity", "https://www.w3.org/ns/activitystreams#activity"),
        Arguments.of("xsd:doc", "http://www.w3.org/2001/XMLSchema#doc"),
        Arguments.of("schema:person", "http://schema.org/person"),
        Arguments.of("ldp:jobtitle", "http://www.w3.org/ns/ldp#jobtitle"));
  }

  @ParameterizedTest
  @MethodSource("compactStrings")
  void parseCompactStrings(String compact, String expected) {
    TryCreateResults results = CompactedIri.tryCreate(compact);
    assertTrue(results.getSuccess());
    try {
      assertEquals(new URI(expected).toString(), results.getValue().getUri().toString());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "https://www.w3.org/ns/activitystreams#activity",
        "http://www.w3.org/2001/XMLSchema#doc",
        "ftp://example.com/source",
        "user:password@example.com:8080/path?q=a#a:1",
        "as:no:no",
        "as:",
        "invalid-prefix:no",
        "https://192.168.1.1:8080/"
      })
  void fallbackToUri(String uri) {
    TryCreateResults results = CompactedIri.tryCreate(uri);
    assertTrue(results.getSuccess());
    assertNull(results.getValue().getNamespace());
    assertEquals(uri, results.getValue().toString());
  }

  @ParameterizedTest
  @ValueSource(strings = {"as:activity", "xsd:doc", "schema:person", "ldp:jobtitle"})
  void formatCompactUri(String compact) {
    TryCreateResults results = CompactedIri.tryCreate(compact);
    assertTrue(results.getSuccess());
    assertEquals(compact, results.getValue().toCompact());
  }
}
