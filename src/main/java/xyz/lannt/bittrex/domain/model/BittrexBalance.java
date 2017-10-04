package xyz.lannt.bittrex.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.lannt.bittrex.application.client.annotation.BittrexResponseName;
import xyz.lannt.bittrex.application.client.response.bittrex.BittrexResult;
import xyz.lannt.bittrex.domain.vo.CryptoText;
import xyz.lannt.bittrex.domain.vo.CryptoValue;
import xyz.lannt.bittrex.presentation.dto.BalanceDto;

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
