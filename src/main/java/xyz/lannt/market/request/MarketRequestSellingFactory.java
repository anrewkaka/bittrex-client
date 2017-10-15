package xyz.lannt.market.request;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import xyz.lannt.annotation.MarketRequestDto;
import xyz.lannt.constant.Market;
import xyz.lannt.exception.MarketClientException;
import xyz.lannt.market.request.bittrex.BittrexSellingRequest;

public class MarketRequestSellingFactory {

  private Market market;

  private MarketRequestSellingFactory(Market market) {
    this.market = market;
  }

  public static MarketRequestSellingFactory initial(Market market) {
    MarketRequestSellingFactory factory = new MarketRequestSellingFactory(market);
    return factory;
  }

  public MarketRequest create(String apiKey, Object dto) {
    if (ObjectUtils.isEmpty(dto)) {
      throw new MarketClientException("parameter cannot be null.");
    }

    if (ObjectUtils.isEmpty(dto.getClass().getAnnotation(MarketRequestDto.class))) {
      throw new MarketClientException("invalid parameter type.");
    }

    if (Market.BITTREX.equals(market)) {
      BittrexSellingRequest request = new BittrexSellingRequest();
      BeanUtils.copyProperties(dto, request);
      return request;
    }

    throw new MarketClientException("market cannot be found.");
  }
}
