package xyz.lannt.bittrex.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BalanceDto {

  private String currency;

  private Double balance;

  private Double available;

  private Double pending;

  private String address;

}
