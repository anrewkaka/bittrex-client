package xyz.lannt.bittrex.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lannt.bittrex.domain.model.CurrencyPrice;
import xyz.lannt.bittrex.domain.vo.CryptoValue;
import xyz.lannt.bittrex.infrastructure.repository.ExchangeRepository;

@Service
public class ExchangeService {

  @Autowired
  private ExchangeRepository exchangeRepository;

  public void setPrice(String exchange, CurrencyPrice price) {
    exchangeRepository.set(exchange, price);
  }

  public CryptoValue getPrice(String exchange, String currency) {
    return exchangeRepository.get(exchange, currency).priceInAverage();
  }
}
