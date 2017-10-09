package xyz.lannt.bittrex.domain.vo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

public class CryptoText {

  private String value;

  public static CryptoText create(Object value) {
    CryptoText result = new CryptoText();
    result.value = ObjectUtils.isEmpty(value) ? null : String.valueOf(value);
    return result;
  }

  @Override
  public String toString() {
    if (ObjectUtils.isEmpty(value)) {
      return "";
    }
    return value;
  }

  @Override
  public boolean equals(Object obj) {
    return StringUtils.equals(value, String.valueOf(obj));
  }
}
