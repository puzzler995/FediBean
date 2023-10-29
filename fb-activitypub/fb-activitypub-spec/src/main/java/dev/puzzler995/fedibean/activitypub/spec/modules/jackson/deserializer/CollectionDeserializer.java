package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer;

import static dev.puzzler995.fedibean.activitypub.spec.model.constants.APConstants.SPECIAL_METHOD_SUFFIXES;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.puzzler995.fedibean.activitypub.spec.model.Collection;
import dev.puzzler995.fedibean.activitypub.spec.model.CollectionPage;
import dev.puzzler995.fedibean.activitypub.spec.model.CompactedIri;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class CollectionDeserializer extends StdDeserializer<Collection> {
  public CollectionDeserializer() {
    this(Collection.class);
  }

  public CollectionDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Collection deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = p.getCodec().readTree(p);

    if (!node.isObject()) {
      String id = node.asText();
      if (id != null) {
        Collection value = new Collection();
        value.setId(new CompactedIri(id));
        value.setType((List<String>) null);
        return value;
      }
      return null;
    }

    Collection collection =
        (StringUtils.equalsIgnoreCase(node.get("type").asText(), "CollectionPage")
                || StringUtils.equalsIgnoreCase(node.get("type").asText(), "OrderedCollectionPage"))
            ? new CollectionPage()
            : new Collection();

    for (Iterator<Entry<String, JsonNode>> entries = node.fields(); entries.hasNext(); ) {
      Entry<String, JsonNode> entry = entries.next();
      String key = entry.getKey();

      try {
        Method[] methods = collection.getClass().getMethods();
        String setterName;
        if (SPECIAL_METHOD_SUFFIXES.containsKey(key)) {
          setterName = "set" + SPECIAL_METHOD_SUFFIXES.get(key);
        } else {
          setterName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
        }
        Method method =
            Arrays.stream(methods)
                .filter(m -> m.getName().equalsIgnoreCase(setterName))
                .findFirst()
                .orElse(null);
        if (method != null) {
          var value = p.getCodec().treeToValue(entry.getValue(), method.getParameterTypes()[0]);
          method.invoke(collection, value);
        }
      } catch (InvocationTargetException | IllegalAccessException e) {
        // TODO: handle
      }
    }

    return collection;
  }
}
