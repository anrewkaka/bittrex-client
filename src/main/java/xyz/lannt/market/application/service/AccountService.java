package xyz.lannt.market.application.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lannt.market.application.client.BittrexMarketClient;
import xyz.lannt.market.application.exception.BittrexClientException;
import xyz.lannt.market.domain.model.BalanceProfit;
import xyz.lannt.market.domain.model.BittrexBalances;
import xyz.lannt.market.domain.model.CurrencyPrices;
import xyz.lannt.market.domain.model.MarketSummaries;
import xyz.lannt.market.infrastructure.repository.ExchangeRepository;
import xyz.lannt.market.presentation.dto.BalanceDto;
import xyz.lannt.market.presentation.dto.BalanceProfitDto;

@Service
public class AccountService {

  private static final String ERROR_MESSAGE_CURRENCY_NOT_FOUND = "currency not found!!";

  @Autowired
  private BittrexMarketClient bittrexMarketClient;

  @Autowired
  private MarketService marketService;

  @Autowired
  private ExchangeRepository exchangeRepository;

  public List<BalanceDto> getBalances() {
    return BittrexBalances.fromResponse(bittrexMarketClient.getBalances())
        .removeEmpty()
        .toDtoes();
  }

  public BalanceDto getBalance(String currency) {
    return BittrexBalances.fromResponse(bittrexMarketClient.getBalances())
        .find(currency)
        .orElseThrow(() -> new BittrexClientException(ERROR_MESSAGE_CURRENCY_NOT_FOUND))
        .toDto();
  }

  public List<BalanceProfitDto> getProfit(String baseCurrency) {
    BittrexBalances balances = BittrexBalances.fromResponse(bittrexMarketClient.getBalances());
    MarketSummaries markets = marketService.getSummaries().find(balances.getMarketNames(baseCurrency));
    CurrencyPrices currencyPrices = exchangeRepository.multiGet("bittrex", balances.getCurrencies());

    return balances.removeEmpty().stream()
        .filter(e -> !e.getCurrency().toString().equals(baseCurrency.equals("BTC") ? "USDT" : "BTC"))
        .filter(e -> e.nonBaseCurrency(baseCurrency))
        .map(e -> {
          String marketName = e.getMarketName(baseCurrency);
          return BalanceProfit.create(e, markets.find(marketName), currencyPrices.find(e.getCurrency()));
        })
        .map(BalanceProfit::toDto)
        .collect(toList());

  }
}
