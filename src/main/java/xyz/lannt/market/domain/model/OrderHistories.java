package xyz.lannt.market.domain.model;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import xyz.lannt.market.application.client.response.bittrex.BittrexOrderHistoryResponse;
import xyz.lannt.market.domain.vo.MarketNames;
import xyz.lannt.market.presentation.dto.OrderHistoryDto;

@AllArgsConstructor
public class OrderHistories {

  private List<OrderHistory> values;

  public static OrderHistories fromResponse(BittrexOrderHistoryResponse response) {
    return response.getResult().stream()
        .map(OrderHistory::fromResult)
        .collect(collectingAndThen(toList(), OrderHistories::new));
  }

  public List<OrderHistoryDto> toDtoes() {
    return values.stream()
        .map(OrderHistory::toDto)
        .collect(toList());
  }

  public OrderHistories find(String marketName) {
    return values.stream()
        .filter(e -> StringUtils.equals(e.getExchange().toString(), marketName))
        .collect(collectingAndThen(toList(), OrderHistories::new));
  }

  public Optional<OrderHistory> findLatest() {
    return values.stream()
        .sorted(OrderHistory.getComparator())
        .findFirst();
  }

  public OrderHistories findLastedBuying(MarketNames markets) {
    return values.stream()
        .filter(e -> markets.contains(e.getExchange().toString()))
        .filter(OrderHistory::isBuying)
        .collect(groupingBy(OrderHistory::getExchange))
        .values().stream()
        .map(OrderHistories::new)
        .map(OrderHistories::findLatest)
        .map(e -> e.orElse(null))
        .collect(collectingAndThen(toList(), OrderHistories::new));
  }
}
