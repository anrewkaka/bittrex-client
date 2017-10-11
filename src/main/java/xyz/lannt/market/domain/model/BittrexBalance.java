package xyz.lannt.market.domain.model;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.lannt.market.application.client.annotation.BittrexResponseName;
import xyz.lannt.market.application.client.response.bittrex.BittrexResult;
import xyz.lannt.market.domain.vo.CryptoText;
import xyz.lannt.market.domain.vo.CryptoValue;
import xyz.lannt.market.presentation.dto.BalanceDto;

@NoArgsConstructor
public class BittrexBalance {

  @Getter
  @BittrexResponseName("Currency")
  private CryptoText currency;

  @Getter
  @BittrexResponseName("Balance")
  private CryptoValue balance;

  @BittrexResponseName("Available")
  private CryptoValue available;

  @BittrexResponseName("Pending")
  private CryptoValue pending;

  @BittrexResponseName("CryptoAddress")
  private CryptoText address;

  public static BittrexBalance fromResult(BittrexResult result) {
    return result.toModel(BittrexBalance.class);
  }

  public String getMarketName(String baseCurrency) {
    return String.join("-", baseCurrency, currency.toString());
  }

  public boolean nonBaseCurrency(String baseCurrency) {
    return !StringUtils.equals(currency.toString(), baseCurrency);
  }

  public BalanceDto toDto() {
    return BalanceDto.builder()
        .currency(currency.toString())
        .balance(balance)
        .available(available)
        .address(address.toString())
        .build();
  }

  public boolean isGreaterThanZero() {
    return balance.isGreaterThanZero();
  }
}
