package xyz.lannt.market.presentation.dto;

import lombok.Data;

@Data
public class CurrencySellDto {

  private String name;

  private String baseCurrency;

  private String price;

  private String quantity;
}
