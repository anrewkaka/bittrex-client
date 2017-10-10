package xyz.lannt.bittrex.infrastructure.repository;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import xyz.lannt.bittrex.domain.model.CurrencyPrice;
import xyz.lannt.bittrex.domain.model.CurrencyPrices;
import xyz.lannt.bittrex.domain.vo.CryptoTimestamp;
import xyz.lannt.bittrex.domain.vo.CryptoValue;

@Repository
public class ExchangeRepository {

  @Autowired
  private StringRedisTemplate redisTemplate;

  public CurrencyPrices multiGet(String exchange, String ... currencies) {
    return Arrays.asList(currencies).stream()
        .map(e -> get(exchange, e))
        .reduce((e1, e2) -> e1.merge(e2))
        .get();
  }

  public CurrencyPrices get(String exchange, String currency) {
    return redisTemplate.opsForHash().entries(createKey(exchange, currency)).entrySet().stream()
        .map(e -> new CurrencyPrice(currency, CryptoValue.create(e.getValue()), CryptoTimestamp.create(e.getKey())))
        .collect(collectingAndThen(toList(), CurrencyPrices::new));
  }

  public void set(String exchange, CurrencyPrice value) {
    redisTemplate.opsForHash().put(createKey(exchange, value.getName().toString()), value.getDatetime().toString(), value.getPrice().toString());
  }

  private String createKey(String exchange, String currency) {
    return String.join(":", "prices", exchange, currency);
  }
}
