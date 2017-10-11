package xyz.lannt.bittrex.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lannt.bittrex.application.client.BittrexMarketClient;
import xyz.lannt.bittrex.application.client.request.BittrexSellingRequest;
import xyz.lannt.bittrex.application.client.response.bittrex.BittrexResult;
import xyz.lannt.bittrex.application.property.BittrexMarketProperty;
import xyz.lannt.bittrex.domain.model.BittrexSelling;
import xyz.lannt.bittrex.domain.model.CurrencyPrice;
import xyz.lannt.bittrex.domain.vo.CryptoValue;
import xyz.lannt.bittrex.infrastructure.repository.ExchangeRepository;
import xyz.lannt.bittrex.presentation.dto.CurrencyPriceDetailDto;
import xyz.lannt.bittrex.presentation.dto.CurrencySellDto;

@Service
public class ExchangeService {

  @Autowired
  private ExchangeRepository exchangeRepository;

  @Autowired
  private BittrexMarketClient bittrexMarketClient;

  @Autowired
  private BittrexMarketProperty bittrexMarketProperty;

  public void setPrice(String exchange, CurrencyPrice price) {
    exchangeRepository.set(exchange, price);
  }

  public List<CurrencyPriceDetailDto> getPrices(String exchange) {
    return exchangeRepository.get(exchange).toDtoes();
  }

  public CryptoValue getPrice(String exchange, String currency) {
    return exchangeRepository.get(exchange, currency).priceInAverage();
  }

  public void deletePrice(String exchange, String currency) {
    exchangeRepository.delete(exchange, currency);
  }

  public BittrexSelling sell(CurrencySellDto dto) {
    BittrexResult result =  bittrexMarketClient.sell(
        BittrexSellingRequest.builder()
          .apiKey(bittrexMarketProperty.getApiKey())
          .market(String.join("_", dto.getName(), dto.getBaseCurrency()))
          .quantity(dto.getQuantity())
          .rate(dto.getPrice())
          .build())
        .getResult().stream().findFirst().orElseGet(null);
    return BittrexSelling.fromResult(result);
  }
}
