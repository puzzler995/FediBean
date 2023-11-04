package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer.CompactedIriSerializer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@RequiredArgsConstructor
@JsonSerialize(using = CompactedIriSerializer.class)
public class CompactedIri {
  public static final Map<String, String> namespaces =
      Map.ofEntries(
          Map.entry("dc11", "http://purl.org/dc/elements/1.1/"),
          Map.entry("dcterms", "http://purl.org/dc/terms/"),
          Map.entry("cred", "https://w3id.org/credentials#"),
          Map.entry("foaf", "http://xmlns.com/foaf/0.1/"),
          Map.entry("geojson", "https://purl.org/geojson/vocab#"),
          Map.entry("prov", "http://www.w3.org/ns/prov#"),
          Map.entry("i18n", "https://www.w3.org/ns/i18n#"),
          Map.entry("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#"),
          Map.entry("schema", "http://schema.org/"),
          Map.entry("skos", "http://www.w3.org/2004/02/skos/core#"),
          Map.entry("xsd", "http://www.w3.org/2001/XMLSchema#"),
          Map.entry("as", "https://www.w3.org/ns/activitystreams#"),
          Map.entry("ldp", "http://www.w3.org/ns/ldp#"),
          Map.entry("vcard", "http://www.w3.org/2006/vcard/ns#"),
          Map.entry("ostatus", "http://ostatus.org#"),
          Map.entry("toot", "http://joinmastodon.org/ns#"),
          Map.entry("fedibird", "http://fedibird.com/ns#"),
          Map.entry("misskey", "https://misskey-hub.net/ns#"),
          Map.entry("litepub", "http://litepub.social/ns#"));

  private final URI uri;
  private String namespace;
  private String prefix;
  private String suffix;

  public CompactedIri(String namespace, String prefix, String suffix) {
    this.uri = URI.create(namespace + suffix);
    this.namespace = namespace;
    this.prefix = prefix;
    this.suffix = suffix;
  }

  public CompactedIri(String prefix, String suffix) {
    this(namespaces.get(prefix), prefix, suffix);
  }

  public CompactedIri(String uri) {
    this.uri = URI.create(uri);
    this.namespace = null;
    this.prefix = null;
    this.suffix = null;
  }

  public static CompactedIri fromString(String s) {
    return new CompactedIri(s);
  }

  public static CompactedIri fromUri(URI uri) {
    return new CompactedIri(uri.toString());
  }

  public static TryCreateResults tryCreate(String compactUri) {
    String prefix = null;
    String suffix = null;
    TryCreateResults result = new TryCreateResults(Boolean.FALSE, null);
    try {
      if ((new URI(compactUri)).getHost() == null && !compactUri.startsWith("http")) {
        String[] parts = compactUri.split(":");
        if (parts.length == 2) {
          prefix = parts[0];
          suffix = parts[1];
          if (namespaces.containsKey(prefix)
              && StringUtils.isNotEmpty(prefix)
              && StringUtils.isNotEmpty(suffix)) {
            result.setSuccess(Boolean.TRUE);
            result.setValue(new CompactedIri(prefix, suffix));
          }
        }
      }
    } catch (URISyntaxException e) {
      // TODO: Handle
    }
    if (!result.getSuccess()) {
      URI uri;
      try {
        uri = new URI(compactUri);
        result.setSuccess(Boolean.TRUE);
        result.setValue(new CompactedIri(uri.toString()));
      } catch (URISyntaxException ignored) {
        // TODO: Handle
      }
    }
    return result;
  }

  public String toCompact() {
    return (prefix != null) ? prefix + ":" + suffix : uri.toString();
  }

  @JsonGetter
  @Override
  public String toString() {
    return toCompact();
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TryCreateResults {
    private Boolean success;
    private CompactedIri value;
  }
}
