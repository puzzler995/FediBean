package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer;

import static dev.puzzler995.fedibean.activitypub.spec.modules.jackson.helper.JacksonHelper.writeIfNotNull;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dev.puzzler995.fedibean.activitypub.spec.model.Link;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

//@JsonComponent
public class LinkSerializer extends JsonSerializer<Link> {

  @Override
  public void serialize(Link value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {

    if (value != null) {
      if (isSimpleLink(value)) {
        gen.writeString(value.getHref().toString());
      } else {
        gen.writeStartObject();
        if (value.getHref() != null) gen.writeStringField("href", value.getHref().toString());
        if (value.getRel() != null) writeIfNotNull(gen, "rel", value.getRel());
        if (value.getMediaType() != null) writeIfNotNull(gen, "mediaType", value.getMediaType());
        if (value.getName() != null) writeIfNotNull(gen, "name", value.getName());
        if (value.getNameMap() != null) writeIfNotNull(gen, "nameMap", value.getNameMap());
        if (value.getHreflang() != null)
          writeIfNotNull(gen, "hreflang", value.getHreflang().getLanguage());
        if (value.getHeight() != null) writeIfNotNull(gen, "height", value.getHeight());
        if (value.getWidth() != null) writeIfNotNull(gen, "width", value.getWidth());
        if (value.getSummary() != null) writeIfNotNull(gen, "summary", value.getSummary());
        if (value.getPreview() != null) writeIfNotNull(gen, "preview", value.getPreview());
        if (value.getId() != null
            && !StringUtils.equalsIgnoreCase(value.getHref().toString(), value.getId().toString()))
          writeIfNotNull(gen, "id", value.getId().toString());
        if (value.getType() != null) writeIfNotNull(gen, "type", value.getType());
        //if (value.getJsonContext() != null) writeIfNotNull(gen, "@context", value.getJsonContext());
        gen.writeEndObject();
      }
    }
  }

  private boolean isSimpleLink(Link value) {
    return value.getHref() != null
        && value.getRel() == null
        && value.getMediaType() == null
        && value.getName() == null
        && value.getHreflang() == null
        && value.getHeight() == null
        && value.getWidth() == null;
  }
}
