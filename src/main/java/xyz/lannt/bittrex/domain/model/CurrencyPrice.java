package xyz.lannt.bittrex.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import xyz.lannt.bittrex.domain.vo.CryptoText;
import xyz.lannt.bittrex.domain.vo.CryptoTimestamp;
import xyz.lannt.bittrex.domain.vo.CryptoValue;
import xyz.lannt.bittrex.presentation.dto.CurrencyPriceRegistrationDto;

public class CurrencyPrice {

  private CurrencyPrice() { }

  @Builder
  public CurrencyPrice(String name, String price, CryptoTimestamp datetime) {
    this.name = CryptoText.create(name);
    this.price = CryptoValue.create(price);
    this.datetime = datetime;
  }

  public CurrencyPrice(String name, CryptoValue price, CryptoTimestamp datetime) {
    this.name = CryptoText.create(name);
    this.price = price;
    this.datetime = datetime;
  }

  public CurrencyPrice(String name, CryptoValue price) {
    this.name = CryptoText.create(name);
    this.price = price;
    datetime = CryptoTimestamp.create(LocalDateTime.now());
  }

  public static CurrencyPrice fromDto(CurrencyPriceRegistrationDto dto) {
    CurrencyPrice price = new CurrencyPrice();
    price.name = CryptoText.create(dto.getName());
    price.price = CryptoValue.create(dto.getPrice());
    price.datetime = CryptoTimestamp.now();
    return price;
  }

  @Getter
  private CryptoText name;

  @Getter
  private CryptoValue price;

  @Getter
  private CryptoTimestamp datetime;
}
