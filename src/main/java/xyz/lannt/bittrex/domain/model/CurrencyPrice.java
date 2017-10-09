package xyz.lannt.bittrex.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import xyz.lannt.bittrex.domain.vo.CryptoText;
import xyz.lannt.bittrex.domain.vo.CryptoTimestamp;
import xyz.lannt.bittrex.domain.vo.CryptoValue;

public class CurrencyPrice {

  @Builder
  public CurrencyPrice(String name, String price, LocalDateTime datetime) {
    this.name = CryptoText.create(name);
    this.price = CryptoValue.create(price);
    this.datetime = datetime;
  }

  public CurrencyPrice(String name, CryptoValue price, CryptoTimestamp datetime) {
    this.name = CryptoText.create(name);
    this.price = price;
    this.datetime = datetime.localDatetime();
  }

  public CurrencyPrice(String name, CryptoValue price) {
    this.name = CryptoText.create(name);
    this.price = price;
    datetime = LocalDateTime.now();
  }

  @Getter
  private CryptoText name;

  @Getter
  private CryptoValue price;

  @Getter
  private LocalDateTime datetime;
}
