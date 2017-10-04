package xyz.lannt.bittrex.domain.model;

import java.util.Optional;

import xyz.lannt.bittrex.application.exception.BittrexClientException;
import xyz.lannt.bittrex.domain.vo.CryptoValue;
import xyz.lannt.bittrex.presentation.dto.BalanceProfitDto;

public class BalanceProfit {

  private static double BITTREX_FEE = 0.0005;

  private static final String ERROR_MESSAGE_MARKET_NOT_FOUND = "market not found!!";

  private static final String ERROR_MESSAGE_ORDER_NOT_FOUND = "order not found!!";

  private String name;

  private CryptoValue amount;

  private CryptoValue buyPrice;

  private CryptoValue currentPrice;

  private CryptoValue profit;

  private CryptoValue profitInPercentage;

  public static BalanceProfit create(BittrexBalance balance, Optional<MarketSummary> market, Optional<OrderHistory> order) {
    if (!market.isPresent()) {
      throw new BittrexClientException(ERROR_MESSAGE_MARKET_NOT_FOUND);
    }

    if (!order.isPresent()) {
      throw new BittrexClientException(ERROR_MESSAGE_ORDER_NOT_FOUND);
    }

    BalanceProfit balanceProfit = new BalanceProfit();
    balanceProfit.name = market.get().getName().toString();
    balanceProfit.amount = balance.getBalance();
    balanceProfit.buyPrice = CryptoValue.create(order.get().getPrice());
    balanceProfit.currentPrice = CryptoValue.create(market.get().getAsk());

    // profit = current - buy - exchange_fee
    balanceProfit.profit = balanceProfit.currentPrice.subtract(balanceProfit.buyPrice).subtract(CryptoValue.create(BITTREX_FEE));
    // profitInPercentage = profit / current * 100
    balanceProfit.profitInPercentage = balanceProfit.profit.divide(balanceProfit.buyPrice).multiply(CryptoValue.create(100));

    return balanceProfit;
  }

  public BalanceProfitDto toDto() {
    return BalanceProfitDto.builder()
        .name(name)
        .amount(amount)
        .buyPrice(buyPrice)
        .currentPrice(currentPrice)
        .profit(profit)
        .profitInPercentage(profitInPercentage)
        .build();
  }
}
