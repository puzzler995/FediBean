package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer;

import static dev.puzzler995.fedibean.activitypub.spec.model.constants.APConstants.SPECIAL_METHOD_SUFFIXES;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.puzzler995.fedibean.activitypub.spec.model.Collection;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class CollectionSerializer extends JsonSerializer<Collection> {

  @Override
  public void serialize(Collection value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    if (value != null) {
      JsonNodeFactory nodeFactory = JsonNodeFactory.instance;
      ObjectNode buffer = nodeFactory.objectNode();
      //      gen.writeStartObject();
      Method[] methods = value.getClass().getMethods();
      Boolean notEmpty = Boolean.FALSE;
      for (Method method : methods) {
        if (!StringUtils.equalsIgnoreCase(method.getName(), "getClass")
            && method.getName().startsWith("get")) {
          String key = method.getName().substring(3);
          key = key.substring(0, 1).toLowerCase() + key.substring(1);
          Object result;
          try {
            result = method.invoke(value);
          } catch (Exception e) {
            result = null;
          }
          if (!StringUtils.equalsIgnoreCase(key, "Items")
              || value.getType().contains("OrderedCollection")
              || value.getType().contains("OrderedCollectionPage")) {
            String finalKey = key;
            String[] newKey = {key};
            SPECIAL_METHOD_SUFFIXES.entrySet().stream()
                .filter(entry -> StringUtils.equalsIgnoreCase(entry.getValue(), finalKey))
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresent(matchingKey -> newKey[0] = matchingKey);
            key = newKey[0];
          }
          if (result != null) {
            if (result instanceof List) {
              if (!((List<?>) result).isEmpty()) {
                buffer.putPOJO(key, result);
                if (!StringUtils.equalsIgnoreCase(key, "type")) notEmpty = Boolean.TRUE;
                //                gen.writeArrayFieldStart(key);
                //                for (Object item : (List<?>) result) {
                //                  gen.writeObject(item);
                //                }
                //                gen.writeEndArray();
              }
            } else if (result instanceof Map) {
              if (!((Map<?, ?>) result).isEmpty()) {
                buffer.putPOJO(key, result);
                if (!StringUtils.equalsIgnoreCase(key, "type")) notEmpty = Boolean.TRUE;
                //                gen.writeObjectFieldStart(key);
                //                for (Map.Entry<?, ?> entry : ((Map<?, ?>) result).entrySet()) {
                //                  gen.writeObjectField(entry.getKey().toString(),
                // entry.getValue());
                //                }
                //                gen.writeEndObject();
              }
            } else {
              buffer.putPOJO(key, result);
              if (!StringUtils.equalsIgnoreCase(key, "type")) notEmpty = Boolean.TRUE;
              //              gen.writeObjectField(key, result);
            }
          }
        }
      }
      if (Boolean.TRUE.equals(notEmpty)) gen.writeTree(buffer);
      //      gen.writeEndObject();
    }
  }
}
