package xyz.lannt.bittrex.domain.model;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;
import xyz.lannt.bittrex.application.client.response.bittrex.BittrexMarketSummariesResponse;
import xyz.lannt.bittrex.presentation.dto.MarketSummaryDto;

@AllArgsConstructor
public class MarketSummaries {

  private List<MarketSummary> values;

  public static MarketSummaries fromResponse(BittrexMarketSummariesResponse response) {
    return response.result.stream()
        .map(MarketSummary::fromLinkedTreeMap)
        .collect(collectingAndThen(toList(), MarketSummaries::new));
  }

  public Optional<MarketSummary> find(String market) {
    return values.stream()
        .filter(e -> StringUtils.equals(e.getName().toString(), market))
        .findFirst();
  }

  public MarketSummaries find(String[] markets) {
    if (ObjectUtils.isEmpty(markets)) {
      return this;
    }

    return values.stream()
        .filter(e -> Arrays.asList(markets).contains(e.getName().toString()))
        .collect(collectingAndThen(toList(), MarketSummaries::new));
  }

  public List<MarketSummaryDto> toDtoes() {
    return values.stream()
        .map(MarketSummary::toDto)
        .collect(Collectors.toList());
  }
}
