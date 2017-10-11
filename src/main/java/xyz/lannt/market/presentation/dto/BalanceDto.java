package xyz.lannt.market.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import xyz.lannt.market.domain.vo.CryptoValue;

@Data
@Builder
@AllArgsConstructor
public class BalanceDto {

  private String currency;

  private CryptoValue balance;

  private CryptoValue available;

  private CryptoValue pending;

  private String address;

}
