package xyz.lannt.market.application.client.response.bittrex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.util.ObjectUtils;

import com.google.gson.internal.LinkedTreeMap;

import xyz.lannt.market.application.client.annotation.BittrexResponseName;
import xyz.lannt.market.application.exception.BittrexClientException;
import xyz.lannt.market.domain.vo.CryptoText;
import xyz.lannt.market.domain.vo.CryptoTimestamp;
import xyz.lannt.market.domain.vo.CryptoValue;

public class BittrexResult extends LinkedHashMap<String, Object> {

  private static final long serialVersionUID = 2440744357123016344L;

  public BittrexResult(LinkedTreeMap<String, Object> map) {
    super(map);
  }

  public <T> T toModel(Class<T> target) {
    T model;
    try {
      model = target.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new BittrexClientException(e);
    }

    for (Field field : getFields(target)) {
      setDataToField(model, field);
    }

    return model;
  }

  private <T> void setDataToField(T model, Field field) {
    String bittrexResponseName = field.getAnnotation(BittrexResponseName.class).value();
    Object value = get(bittrexResponseName);
    Class type = field.getType();
    field.setAccessible(true);
    try {
      if (CryptoValue.class.equals(type)) {
        field.set(model, CryptoValue.create(value));
      } else if (CryptoText.class.equals(type)) {
        field.set(model, CryptoText.create(value));
      } else if (CryptoTimestamp.class.equals(type)) {
        field.set(model, CryptoTimestamp.create(value));
      }
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new BittrexClientException(e);
    }

  }

  private List<Field> getFields(Class<?> clazz) {
    List<Field> fields = new ArrayList<Field>();
    for (Field field : clazz.getDeclaredFields()) {
      fields.add(field);
    }

    return fields;
  }
}
