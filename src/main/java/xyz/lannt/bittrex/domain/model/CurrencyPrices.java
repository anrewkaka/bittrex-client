package xyz.lannt.bittrex.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import xyz.lannt.bittrex.domain.vo.CryptoText;

@AllArgsConstructor
public class CurrencyPrices {

  private List<CurrencyPrice> values;

  public Stream<CurrencyPrice> stream() {
    return values.stream();
  }

  public List<CurrencyPrice> getAll() {
    return Collections.unmodifiableList(values);
  }

  public Optional<CurrencyPrice> find(CryptoText currency) {
    return stream()
        .filter(e -> e.equals(currency))
        .findFirst();
  }

  public CurrencyPrices merge(CurrencyPrices source) {
    values.addAll(source.getAll());
    return this;
  }
}
