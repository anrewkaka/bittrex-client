package xyz.lannt.bittrex.domain.vo;

import java.math.BigDecimal;

import org.springframework.util.ObjectUtils;

public class CryptoValue {

  private BigDecimal value;

  public static CryptoValue create(String value) {
    CryptoValue result = new CryptoValue();
    try {
      result.value = BigDecimal.valueOf(Double.parseDouble(value));
    } catch (Exception e) {
      result.value = BigDecimal.ZERO;
    }

    return result;
  }

  public static CryptoValue create(Double value) {
    CryptoValue result = new CryptoValue();
    result.value = BigDecimal.valueOf(value);
    return result;
  }

  public static CryptoValue create(Object value) {
    if (ObjectUtils.isEmpty(value)) {
      return CryptoValue.create(0);
    }

    if (value instanceof Double) {
      return CryptoValue.create((Double) value);
    }

    return CryptoValue.create(value.toString());
  }

  public Double doubleValue() {
    return value.doubleValue();
  }

  public boolean isGreaterThanZero() {
    return value.compareTo(BigDecimal.ZERO) > 0;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
