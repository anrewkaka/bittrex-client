package xyz.lannt.market.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xyz.lannt.market.application.client.BittrexMarketClient;
import xyz.lannt.market.application.client.FakeBittrexMarketClient;
import xyz.lannt.market.application.client.MarketClientSetting;
import xyz.lannt.market.application.property.BittrexMarketProperty;

@Configuration
public class BittrexMarketClientConfig {

  @Autowired
  private BittrexMarketProperty bittrexMarketProperty;

  @Bean
  public BittrexMarketClient bittrexMarketClient() {
    return new FakeBittrexMarketClient(MarketClientSetting.builder()
        .baseUrl(bittrexMarketProperty.getBaseUrl())
        .apiKey(bittrexMarketProperty.getApiKey())
        .sercretKey(bittrexMarketProperty.getSecret())
        .build());
  }

//  @Bean
//  public BittrexMarketClient bittrexMarketClient() {
//    return new BittrexMarketClient(MarketClientSetting.builder()
//        .baseUrl(bittrexMarketProperty.getBaseUrl())
//        .apiKey(bittrexMarketProperty.getApiKey())
//        .sercretKey(bittrexMarketProperty.getSecret())
//        .build());
//  }
}
