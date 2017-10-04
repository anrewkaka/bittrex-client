package xyz.lannt.bittrex.presentation.dto;

import lombok.Builder;
import xyz.lannt.bittrex.domain.vo.CryptoValue;

@Builder
public class MarketCompareDto {

  public MarketSummaryDto base;

  public MarketSummaryDto source;

  public MarketSummaryDto target;

  public CryptoValue last;

  public CryptoValue bid;

  public CryptoValue ask;
}
