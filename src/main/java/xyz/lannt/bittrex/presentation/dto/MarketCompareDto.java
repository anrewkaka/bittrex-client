package xyz.lannt.bittrex.presentation.dto;

import lombok.Builder;

@Builder
public class MarketCompareDto {

  public MarketSummaryDto base;

  public MarketSummaryDto source;

  public MarketSummaryDto target;

  public Double last;

  public Double bid;

  public Double ask;
}
