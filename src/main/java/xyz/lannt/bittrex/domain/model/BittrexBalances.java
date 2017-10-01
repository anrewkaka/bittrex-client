package xyz.lannt.bittrex.domain.model;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.internal.LinkedTreeMap;

import lombok.AllArgsConstructor;
import xyz.lannt.bittrex.presentation.dto.BalanceDto;

@AllArgsConstructor
public class BittrexBalances {

  private List<BittrexBalance> values;

  public static BittrexBalances fromLinkedTreeMap(List<LinkedTreeMap<String, Object>> treeMap) {
    return treeMap.stream()
        .map(BittrexBalance::fromLinkedTreeMap)
        .collect(collectingAndThen(toList(), BittrexBalances::new));
  }

  public Optional<BittrexBalance> find(String currency) {
    return values.stream()
        .filter(e -> StringUtils.equals(e.getCurrency(), currency))
        .findFirst();
  }

  public BittrexBalances removeEmpty() {
    return values.stream()
        .filter(BittrexBalance::isGreaterThanZero)
        .collect(collectingAndThen(toList(), BittrexBalances::new));
  }

  public List<BalanceDto> toDtoes() {
    return values.stream()
        .map(BittrexBalance::toDto)
        .collect(Collectors.toList());
  }
}
