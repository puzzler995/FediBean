package dev.puzzler995.fedibean.test.tests.activitypub.spec.modules;

import static dev.puzzler995.fedibean.activitypub.spec.model.constants.APConstants.SPECIAL_METHOD_SUFFIXES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonTestParser {

  @Autowired public ObjectMapper mapper;

  public void parseJsonTest(String expected, Object actualObj) {
    parseJsonTest(mapper.valueToTree(expected), actualObj);
  }

  public void parseJsonTest(JsonNode expectedObj, Object actualObj) {
    if (expectedObj.isObject()) {
      Iterator<JsonNode> expectedValues = expectedObj.iterator();
      Iterator<String> expectedFields = expectedObj.fieldNames();
      while (expectedValues.hasNext()) {
        String fieldName = expectedFields.next();
        JsonNode field = expectedValues.next();
        String getterName;
        if (SPECIAL_METHOD_SUFFIXES.containsKey(fieldName)) {
          getterName = "get" + SPECIAL_METHOD_SUFFIXES.get(fieldName);
        } else {
          if (StringUtils.equalsIgnoreCase(fieldName.substring(0, 1), "@"))
            fieldName = fieldName.substring(1);
          getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
        if (field.isArray()) {
          try {
            Method method = actualObj.getClass().getMethod(getterName);
            if (method != null) {
              Object actualFieldValue = method.invoke(actualObj);
              if (actualFieldValue instanceof List) {
                parseJsonArrayTest(field, (List<Object>) actualFieldValue);
              }
            }
          } catch (Exception e) {
            fail("Failed due to exception: ", e);
          }
        } else if (field.isObject()) {
          try {
            Method method = actualObj.getClass().getMethod(getterName);
            if (method != null) {
              Object actualFieldValue = method.invoke(actualObj);
              if (actualFieldValue != null) {
                parseJsonTest(field, actualFieldValue);
              }
            }
          } catch (Exception e) {
            fail("Failed due to exception: ", e);
          }
        } else if (field.isTextual()) {
          try {
            Method method = actualObj.getClass().getMethod(getterName);
            if (method != null) {
              Object actualFieldValue = method.invoke(actualObj);
              if (actualFieldValue instanceof String) {
                assertThat(actualFieldValue).isEqualTo(field.asText());
              } else {
                //TODO
              }
            }
          } catch (Exception e) {
            fail("Failed due to exception: ", e);
          }
        } else {
          fail("Unknown field type");
        }
      }
    } else if (expectedObj.isArray()) {
      parseJsonArrayTest(expectedObj, (List<Object>) actualObj);
    } else if (expectedObj.isTextual()) {
      if (actualObj instanceof String) assertThat(actualObj).isEqualTo(expectedObj.asText());
      else assertThat(actualObj).hasToString(expectedObj.asText());
    } else {
      fail("Unknown field type");
    }
  }

  public void parseJsonArrayTest(JsonNode expectedNode, List<Object> actualArray) {
    if (expectedNode.isArray()) {
      int arraySize = expectedNode.size();
      List<Object> elements = actualArray;
      assertThat(elements.size()).isEqualTo(arraySize);
      for (int i = 0; i < arraySize; i++) {
        parseJsonTest(expectedNode.get(i), elements.get(i));
      }
    }
  }
}
