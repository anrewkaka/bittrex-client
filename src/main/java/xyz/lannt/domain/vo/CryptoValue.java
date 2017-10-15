package xyz.lannt.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.springframework.util.ObjectUtils;

public class CryptoValue {

  private final BigDecimal value;

  public CryptoValue(BigDecimal value) {
    this.value = value;
    this.value.setScale(8, RoundingMode.HALF_EVEN);
  }

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

  public boolean isZero() {
    return value.compareTo(BigDecimal.ZERO) == 0;
  }

  public boolean isGreaterThanZero() {
    return value.compareTo(BigDecimal.ZERO) > 0;
  }

  public CryptoValue multiply(CryptoValue target) {
    return new CryptoValue(value.multiply(target.value));
  }

  public CryptoValue divide(CryptoValue target) {
    if (target.isZero()) {
      return CryptoValue.create(this);
    }
    return new CryptoValue(value.divide(target.value, 8, RoundingMode.HALF_UP));
  }

  public CryptoValue add(CryptoValue target) {
    return new CryptoValue(value.add(target.value));
  }

  public CryptoValue subtract(CryptoValue target) {
    return new CryptoValue(value.subtract(target.value));
  }

  @Override
  public String toString() {
    if (ObjectUtils.isEmpty(value)) {
      return "";
    }

    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(8);
    df.setMinimumFractionDigits(8);
    df.setGroupingUsed(false);

    return df.format(value);
  }
}
