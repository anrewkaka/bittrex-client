package xyz.lannt.bittrex.application.client.request;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.ObjectUtils;

import xyz.lannt.bittrex.application.client.annotation.MarketQueryParam;
import xyz.lannt.bittrex.application.exception.BittrexClientException;

public interface MarketRequest {

  default String toQueryParam() throws IllegalAccessException {
    return getFields(getClass()).stream()
        .filter(e -> !ObjectUtils.isEmpty(getFieldValue(e)))
        .map(e -> getParam(e))
        .reduce((v1, v2) -> String.join("&", v1, v2))
        .orElse("");
  }

  default String getParam(Field field) {
    if (field == null) {
      return "";
    }

    MarketQueryParam annotation = field.getAnnotation(MarketQueryParam.class);
    if (annotation == null) {
      return "";
    }

    if (annotation.value() == null) {
      return "";
    }

    return annotation.value() + "=" + getFieldValue(field);
  }

  default List<Field> getFields(Class<?> clazz) {
    Class<?> superClass = clazz.getSuperclass();
    if (superClass == null) {
      return Collections.emptyList();
    }

    List<Field> fields = new ArrayList<Field>();
    for (Field field : clazz.getDeclaredFields()) {
      fields.add(field);
    }

    fields.addAll(getFields(superClass));

    return fields;
  }

  default String getFieldValue(Field field) {

    field.setAccessible(true);
    Class<?> targetType = field.getType();
    Object fieldData;
    try {
      fieldData = field.get(this);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new BittrexClientException(e);
    }
    if (fieldData == null) {
      return null;
    }

    if (targetType.isPrimitive()) {
      return String.valueOf(fieldData);
    }

    return fieldData.toString();
  }
}
