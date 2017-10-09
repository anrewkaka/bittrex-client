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

  public void setPrice(String exchange, String currency, CryptoValue price) {
    exchangeRepository.set(exchange, new CurrencyPrice(currency, price));
  }
}
