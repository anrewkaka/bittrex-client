package xyz.lannt.bittrex.domain.model;

import com.google.gson.internal.LinkedTreeMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import xyz.lannt.bittrex.domain.vo.CryptoValue;
import xyz.lannt.bittrex.presentation.dto.BalanceDto;

@Data
@Builder
@AllArgsConstructor
public class BittrexBalance {

  private String currency;

  private CryptoValue balance;

  private CryptoValue available;

  private CryptoValue pending;

  private String address;

  public static BittrexBalance fromLinkedTreeMap(LinkedTreeMap<String, Object> map) {
    return BittrexBalance.builder()
        .currency(String.valueOf(map.get("Currency")))
        .balance(CryptoValue.create(map.get("Balance")))
        .available(CryptoValue.create(map.get("Available")))
        .pending(CryptoValue.create(map.get("Pending")))
        .address(map.get("CryptoAddress") == null ? null : String.valueOf(map.get("CryptoAddress")))
        .build();
  }

  public BalanceDto toDto() {
    return BalanceDto.builder()
        .currency(currency)
        .balance(balance.doubleValue())
        .available(available.doubleValue())
        .address(address)
        .build();
  }

  public boolean isGreaterThanZero() {
    return balance.isGreaterThanZero();
  }
}
