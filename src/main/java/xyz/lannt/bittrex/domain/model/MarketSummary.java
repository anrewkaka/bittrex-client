package xyz.lannt.bittrex.domain.model;

import com.google.gson.internal.LinkedTreeMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import xyz.lannt.bittrex.domain.vo.CryptoText;
import xyz.lannt.bittrex.domain.vo.CryptoTimestamp;
import xyz.lannt.bittrex.domain.vo.CryptoValue;
import xyz.lannt.bittrex.presentation.dto.MarketSummaryDto;

@Builder
@AllArgsConstructor
public class MarketSummary {

  @Getter
  private CryptoText name;

  private CryptoValue hight;

  private CryptoValue low;

  private CryptoValue volume;

  @Getter
  private CryptoValue last;

  private CryptoValue baseVolume;

  private CryptoTimestamp timestamp;

  @Getter
  private CryptoValue bid;

  @Getter
  private CryptoValue ask;

  private CryptoValue openBuyOrders;

  private CryptoValue openSellOrders;

  private CryptoValue prevDay;

  private CryptoTimestamp created;

  private CryptoText displayMarketName;

  public static MarketSummary fromLinkedTreeMap (LinkedTreeMap<String, Object> map) {
    return MarketSummary.builder()
        .name(CryptoText.create(map.get("MarketName")))
        .hight(CryptoValue.create(map.get("Hight")))
        .low(CryptoValue.create(map.get("Low")))
        .volume(CryptoValue.create(map.get("Volume")))
        .last(CryptoValue.create(map.get("Last")))
        .baseVolume(CryptoValue.create(map.get("BaseVolume")))
        .timestamp(CryptoTimestamp.create(map.get("TimeStamp")))
        .bid(CryptoValue.create(map.get("Bid")))
        .ask(CryptoValue.create(map.get("Ask")))
        .openBuyOrders(CryptoValue.create(map.get("OpenBuyOrders")))
        .openSellOrders(CryptoValue.create(map.get("OpenSellOrders")))
        .prevDay(CryptoValue.create(map.get("PrevDay")))
        .created(CryptoTimestamp.create(map.get("Created")))
        .displayMarketName(CryptoText.create(map.get("DisplayMarketName")))
        .build();
  }

  public MarketSummaryDto toDto() {
    return MarketSummaryDto.builder()
        .name(name.toString())
        .hight(hight.doubleValue())
        .low(low.doubleValue())
        .volume(volume.doubleValue())
        .last(last.doubleValue())
        .baseVolume(baseVolume.doubleValue())
        .timestamp(timestamp.toString())
        .bid(bid.doubleValue())
        .ask(ask.doubleValue())
        .openBuyOrders(openBuyOrders.doubleValue())
        .openSellOrders(openSellOrders.doubleValue())
        .prevDay(prevDay.doubleValue())
        .created(created.toString())
        .displayMarketName(displayMarketName.toString())
        .build();
  }
}
