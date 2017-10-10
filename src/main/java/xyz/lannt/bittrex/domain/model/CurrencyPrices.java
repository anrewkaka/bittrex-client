package xyz.lannt.bittrex.domain.model;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;
import xyz.lannt.bittrex.domain.vo.CryptoText;
import xyz.lannt.bittrex.domain.vo.CryptoValue;

@AllArgsConstructor
public class CurrencyPrices {

  private static double BITTREX_FEE = 0.25 / 100;

  private List<CurrencyPrice> values;

  public Stream<CurrencyPrice> stream() {
    return values.stream();
  }

  public List<CurrencyPrice> getAll() {
    return Collections.unmodifiableList(values);
  }

  public CurrencyPrices find(CryptoText currency) {
    return stream()
        .filter(e -> e.getName().equals(currency))
        .collect(collectingAndThen(toList(), CurrencyPrices::new));
  }

  public CurrencyPrices merge(CurrencyPrices source) {
    values.addAll(source.getAll());
    return this;
  }

  public CryptoValue priceInAverage() {
    int exchangeNumber = values.size();
    return values.stream()
        .map(e -> e.getPrice())
        .reduce((e1, e2) -> e1.add(e2))
        .orElse(CryptoValue.create(0))
        // sum(e(1)...e(n))/n
        .divide(CryptoValue.create(exchangeNumber))
        // bp = bp * (1 + n * fee)
        .multiply(CryptoValue.create(1 + exchangeNumber * BITTREX_FEE));
  }

  public boolean isEmpty() {
    return ObjectUtils.isEmpty(values);
  }
}
