package xyz.lannt.bittrex.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lannt.bittrex.application.client.BittrexMarketClient;
import xyz.lannt.bittrex.application.exception.BittrexClientException;
import xyz.lannt.bittrex.domain.model.MarketSummaries;
import xyz.lannt.bittrex.presentation.dto.MarketSummaryDto;

@Service
public class MarketService {

  @Autowired
  private BittrexMarketClient bittrexMarketClient;

  public List<MarketSummaryDto> getSummaries(String[] markets) {
    return MarketSummaries.fromResponse(bittrexMarketClient.getMarketSummaries())
        .find(markets)
        .toDtoes();
  }

  public MarketSummaryDto getSummary(String market) {
    return MarketSummaries.fromResponse(bittrexMarketClient.getMarketSummaries())
        .find(market)
        .orElseThrow(() -> new BittrexClientException("market not found!!"))
        .toDto();
  }
}
