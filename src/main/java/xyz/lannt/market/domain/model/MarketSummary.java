package xyz.lannt.market.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.lannt.market.application.client.annotation.BittrexResponseName;
import xyz.lannt.market.application.client.response.bittrex.BittrexResult;
import xyz.lannt.market.domain.vo.CryptoText;
import xyz.lannt.market.domain.vo.CryptoTimestamp;
import xyz.lannt.market.domain.vo.CryptoValue;
import xyz.lannt.market.presentation.dto.MarketSummaryDto;

@NoArgsConstructor
public class MarketSummary {

  @Getter
  @BittrexResponseName("MarketName")
  private CryptoText name;

  @BittrexResponseName("Hight")
  private CryptoValue hight;

  @BittrexResponseName("Low")
  private CryptoValue low;

  @BittrexResponseName("Volume")
  private CryptoValue volume;

  @Getter
  @BittrexResponseName("Last")
  private CryptoValue last;

  @BittrexResponseName("BaseVolume")
  private CryptoValue baseVolume;

  @BittrexResponseName("TimeStamp")
  private CryptoTimestamp timestamp;

  @Getter
  @BittrexResponseName("Bid")
  private CryptoValue bid;

  @Getter
  @BittrexResponseName("Ask")
  private CryptoValue ask;

  @BittrexResponseName("OpenBuyOrders")
  private CryptoValue openBuyOrders;

  @BittrexResponseName("OpenSellOrders")
  private CryptoValue openSellOrders;

  @BittrexResponseName("PrevDay")
  private CryptoValue prevDay;

  @BittrexResponseName("Created")
  private CryptoTimestamp created;

  @BittrexResponseName("DisplayMarketName")
  private CryptoText displayMarketName;

  public static MarketSummary fromResult (BittrexResult result) {
    return result.toModel(MarketSummary.class);
  }

  public MarketSummaryDto toDto() {
    return MarketSummaryDto.builder()
        .name(name.toString())
        .hight(hight)
        .low(low)
        .volume(volume)
        .last(last)
        .baseVolume(baseVolume)
        .timestamp(timestamp.toString())
        .bid(bid)
        .ask(ask)
        .openBuyOrders(openBuyOrders)
        .openSellOrders(openSellOrders)
        .prevDay(prevDay)
        .created(created.toString())
        .displayMarketName(displayMarketName.toString())
        .build();
  }
}
