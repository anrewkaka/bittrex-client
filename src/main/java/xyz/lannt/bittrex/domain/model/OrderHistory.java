package xyz.lannt.bittrex.domain.model;

import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import xyz.lannt.bittrex.application.client.annotation.BittrexResponseName;
import xyz.lannt.bittrex.application.client.response.bittrex.BittrexResult;
import xyz.lannt.bittrex.domain.vo.CryptoText;
import xyz.lannt.bittrex.domain.vo.CryptoTimestamp;
import xyz.lannt.bittrex.domain.vo.CryptoValue;
import xyz.lannt.bittrex.presentation.dto.OrderHistoryDto;

public class OrderHistory {

  @BittrexResponseName("OrderUuid")
  private CryptoText id;

  @Getter
  @BittrexResponseName("Exchange")
  private CryptoText exchange;

  @BittrexResponseName("TimeStamp")
  private CryptoTimestamp date;

  @BittrexResponseName("OrderType")
  private CryptoText type;

  @BittrexResponseName("Limit")
  private CryptoValue limit;

  @BittrexResponseName("Quantity")
  private CryptoValue quantity;

  @BittrexResponseName("QuantityRemaining")
  private CryptoValue quantityRemain;

  @BittrexResponseName("Commission")
  private CryptoValue commission;

  @Getter
  @BittrexResponseName("Price")
  private CryptoValue price;

  @BittrexResponseName("PricePerUnit")
  private CryptoValue pricePerUnit;

  @BittrexResponseName("IsConditional")
  private Boolean isConditional;

  @BittrexResponseName("Condition")
  private CryptoText condition;

  @BittrexResponseName("ConditionTarget")
  private CryptoText conditionTarget;

  @BittrexResponseName("ImmediateOrCancel")
  private Boolean immediateOrCancel;

  public static OrderHistory fromResult (BittrexResult result) {
    return result.toModel(OrderHistory.class);
  }

  public OrderHistoryDto toDto() {
    return OrderHistoryDto.builder()
        .id(id.toString())
        .exchange(exchange.toString())
        .date(date.toString())
        .type(type.toString())
        .limit(limit)
        .quantity(quantity)
        .quantityRemain(quantityRemain)
        .commission(commission)
        .price(price)
        .pricePerUnit(pricePerUnit)
        .isConditional(isConditional)
        .condition(condition.toString())
        .conditionTarget(conditionTarget.toString())
        .immediateOrCancel(immediateOrCancel)
        .build();
  }

  public boolean isBuying() {
    return StringUtils.contains(type.toString(), "BUY");
  }

  public boolean isSelling() {
    return StringUtils.contains(type.toString(), "SELL");
  }

  public static Comparator<OrderHistory> getComparator() {
    return new Comparator<OrderHistory>() {

      @Override
      public int compare(OrderHistory o1, OrderHistory o2) {
        return o1.date.localDatetime().compareTo(o2.date.localDatetime());
      }
    };
  }
}
