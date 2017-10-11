package xyz.lannt.market.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lannt.market.application.client.BittrexMarketClient;
import xyz.lannt.market.application.exception.BittrexClientException;
import xyz.lannt.market.domain.model.MarketComapre;
import xyz.lannt.market.domain.model.MarketSummaries;
import xyz.lannt.market.domain.vo.MarketNames;
import xyz.lannt.market.presentation.dto.MarketCompareDto;
import xyz.lannt.market.presentation.dto.MarketSummaryDto;

@Service
public class MarketService {

  private static final String MARKET_NOTFOUND_MESSAGE = "market not found!!";

  @Autowired
  private BittrexMarketClient bittrexMarketClient;

  public List<MarketSummaryDto> getSummaries(MarketNames markets) {
    return this.getSummaries()
        .find(markets)
        .toDtoes();
  }

  public MarketSummaries getSummaries() {
    return MarketSummaries.fromResponse(bittrexMarketClient.getMarketSummaries());
  }

  public MarketSummaryDto getSummary(String market) {
    return findAll().find(market)
        .orElseThrow(() -> new BittrexClientException(MARKET_NOTFOUND_MESSAGE))
        .toDto();
  }

  public MarketCompareDto getCompare(String base, String source, String target) {
    MarketSummaries marketSummaries = findAll();
    MarketComapre marketCompare = MarketComapre.builder()
        .base(marketSummaries.find(base).orElseThrow(() -> new BittrexClientException(MARKET_NOTFOUND_MESSAGE)))
        .source(marketSummaries.find(source).orElseThrow(() -> new BittrexClientException(MARKET_NOTFOUND_MESSAGE)))
        .target(marketSummaries.find(target).orElseThrow(() -> new BittrexClientException(MARKET_NOTFOUND_MESSAGE)))
        .build();
    return marketCompare.calculate();
  }

  private MarketSummaries findAll() {
    return MarketSummaries.fromResponse(bittrexMarketClient.getMarketSummaries());
  }
}
