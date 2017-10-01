package xyz.lannt.bittrex.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class MarketSummaryDto {

  public String name;

  public Double hight;

  public Double low;

  public Double volume;

  public Double last;

  public Double baseVolume;

  public String timestamp;

  public Double bid;

  public Double ask;

  public Double openBuyOrders;

  public Double openSellOrders;

  public Double prevDay;

  public String created;

  public String displayMarketName;
}
