package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer.ContentMapDeserializer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonDeserialize(using = ContentMapDeserializer.class)
public class ContentMap {
  private final Map<String, String> contentMap = new HashMap<>();
  private final String defaultCulture;
  private final Set<String> languages = contentMap.keySet();

  public ContentMap(String cultureInfo) {
    this.defaultCulture = cultureInfo;
  }

  public ContentMap() {
    this(Locale.ENGLISH.getLanguage());
  }

  public void add(String defaultValue) {
    contentMap.put(defaultCulture, defaultValue);
  }

  public void add(String culture, String value) {
    contentMap.put(culture, value);
  }

  public String get(String key) {
    return contentMap.get(key);
  }

  @Override
  public String toString() {
    String value = contentMap.get(defaultCulture);
    if (value != null) {
      return value;
    } else if (!contentMap.isEmpty()) {
      return contentMap.values().iterator().next();
    } else {
      return "";
    }
  }
}
