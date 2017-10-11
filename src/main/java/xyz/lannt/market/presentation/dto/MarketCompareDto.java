package xyz.lannt.market.presentation.dto;

import lombok.Builder;
import xyz.lannt.market.domain.vo.CryptoValue;

@Builder
public class MarketCompareDto {

  public MarketSummaryDto base;

  public MarketSummaryDto source;

  public MarketSummaryDto target;

  public CryptoValue last;

  public CryptoValue bid;

  public CryptoValue ask;
}
