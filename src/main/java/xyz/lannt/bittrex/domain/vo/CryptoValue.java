package xyz.lannt.bittrex.domain.vo;

import java.math.BigDecimal;

import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CryptoValue {

  private final BigDecimal value;

  public static CryptoValue create(String value) {
    try {
      return new CryptoValue(BigDecimal.valueOf(Double.parseDouble(value)));
    } catch (Exception e) {
      return new CryptoValue(BigDecimal.ZERO);
    }
  }

  public static CryptoValue create(Double value) {
    return new CryptoValue(BigDecimal.valueOf(value));
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

  public CryptoValue multiply(CryptoValue target) {
    return new CryptoValue(value.multiply(target.value));
  }

  public CryptoValue subtract(CryptoValue target) {
    return new CryptoValue(value.subtract(target.value));
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
