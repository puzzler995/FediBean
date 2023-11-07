package dev.puzzler995.fedibean.activitypub.spec.modules;

import static dev.puzzler995.fedibean.activitypub.spec.model.constants.APConstants.CLASS_TYPE_MAP;

import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.core.RDFDataset;
import com.github.jsonldjava.core.RDFDataset.Node;
import com.github.jsonldjava.core.RDFDataset.Quad;
import com.github.jsonldjava.utils.JsonUtils;
import com.google.common.base.CaseFormat;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import dev.puzzler995.fedibean.activitypub.spec.model.CompactedIri;
import dev.puzzler995.fedibean.activitypub.spec.model.Resolvable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JsonLdHandler {
  public <T extends Resolvable> T handle(String json)
      throws IOException,
          InstantiationException,
          IllegalAccessException,
          NoSuchMethodException,
          InvocationTargetException {
    RDFDataset rdf = (RDFDataset) JsonLdProcessor.toRDF(JsonUtils.fromString(json));
    Table<Node, Node, Node> toCreate = TreeBasedTable.create();
    ArrayListMultimap<String, String> blankNodes = ArrayListMultimap.create();
    for (Object item : (ArrayList<?>) rdf.get("@default")) {
      if (item instanceof Quad quad) {
        if (quad.getObject().isBlankNode()) {
          blankNodes.put(quad.getSubject().getValue(), quad.getObject().getValue());
        }
        toCreate.put(quad.getSubject(), quad.getPredicate(), quad.getObject());
      }
    }

    // create all pojos
    Map<String, Object> pojos = new HashMap<>();
    Map<String, Boolean> isParent = new HashMap<>();
    for (Entry<Node, Map<Node, Node>> entry : toCreate.rowMap().entrySet()) {
      var typing =
          entry.getValue().entrySet().stream()
              .filter(
                  e ->
                      e.getKey()
                          .getValue()
                          .equals("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"))
              .findFirst()
              .orElse(null);
      String type = "Generic";
      if (typing != null) {
        type = typing.getValue().getValue();
      }
      var id = entry.getKey().getValue();
      Class<? extends Resolvable> classType = CLASS_TYPE_MAP.get(type);
      if (classType == null) {
        throw new RuntimeException("Class type not found for " + type);
      }
      Constructor<? extends Resolvable> constructor = classType.getConstructor();
      var pojo = constructor.newInstance();
      if (!entry.getKey().isBlankNode())
        pojo.setId(new CompactedIri(id));
      pojos.put(id, pojo);
      isParent.put(id, Boolean.TRUE);
    }
    for (Entry<Node, Map<Node, Node>> entry : toCreate.rowMap().entrySet()) {
      var parent = entry.getKey().getValue();
      var pojo = pojos.get(parent);
      for (Entry<Node, Node> child : entry.getValue().entrySet()) {
        var key = child.getKey().getValue();
        int splitPoint = key.lastIndexOf("#") + 1;
        if (splitPoint == -1) {
          splitPoint = key.lastIndexOf("/") + 1;
        }
        String type = key.substring(splitPoint);
        type = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, type);
        Method setter = pojo.getClass().getMethod("set" + type, Object.class);
        var value = child.getValue().getValue();
        if (pojos.containsKey(value)) {
          isParent.put(value, Boolean.FALSE);
          setter.invoke(pojo, pojos.get(value));
        } else {
          setter.invoke(pojo, value);
        }
      }
    }
    for (Entry<String, Boolean> pojo : isParent.entrySet()) {
      if (Boolean.TRUE.equals(pojo.getValue()))
        return (T) pojos.get(pojo.getKey());
    }
    return null;
  }
}
