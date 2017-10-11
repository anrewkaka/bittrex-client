package xyz.lannt.market.domain.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.util.ObjectUtils;

public class CryptoTimestamp {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  private LocalDateTime value;

  public static CryptoTimestamp now() {
    CryptoTimestamp result = new CryptoTimestamp();
    result.value = LocalDateTime.now();
    return result;
  }

  public static CryptoTimestamp create(Object value) {
    CryptoTimestamp result = new CryptoTimestamp();

    if (ObjectUtils.isEmpty(value)) {
      result.value = null;
      return result;
    }

    result.value = LocalDateTime.parse(value.toString(), formatter);
    return result;
  }

  @Override
  public String toString() {
    if (ObjectUtils.isEmpty(value)) {
      return "";
    }
    return value.format(formatter);
  }

  public LocalDateTime localDatetime() {
    return LocalDateTime.parse(toString(), formatter);
  }
}
