package xyz.lannt.market.response.bittrex;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.ObjectUtils;

import com.google.gson.internal.LinkedTreeMap;

import xyz.lannt.annotation.MarketResponseName;
import xyz.lannt.domain.vo.CryptoText;
import xyz.lannt.domain.vo.CryptoTimestamp;
import xyz.lannt.domain.vo.CryptoValue;
import xyz.lannt.exception.MarketClientException;

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
      throw new MarketClientException(e);
    }

    for (Field field : getFields(target)) {
      setDataToField(model, field);
    }

    return model;
  }

  private <T> void setDataToField(T model, Field field) {
    String bittrexResponseName = field.getAnnotation(MarketResponseName.class).value();
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
      throw new MarketClientException(e);
    }

  }

  private List<Field> getFields(Class<?> clazz) {
    return Stream.of(clazz.getDeclaredFields())
        .filter(e -> !ObjectUtils.isEmpty(e.getAnnotation(MarketResponseName.class)))
        .collect(Collectors.toList());
  }
}
