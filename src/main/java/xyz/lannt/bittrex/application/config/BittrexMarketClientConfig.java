package xyz.lannt.bittrex.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xyz.lannt.bittrex.application.client.BittrexMarketClient;
import xyz.lannt.bittrex.application.client.MarketClientSetting;
import xyz.lannt.bittrex.application.property.BittrexMarketProperty;

@Configuration
public class BittrexMarketClientConfig {

  @Autowired
  private BittrexMarketProperty bittrexMarketProperty;

  @Bean
  public BittrexMarketClient bittrexMarketClient() {
    return new BittrexMarketClient(MarketClientSetting.builder()
        .baseUrl(bittrexMarketProperty.getBaseUrl())
        .apiKey(bittrexMarketProperty.getApiKey())
        .sercretKey(bittrexMarketProperty.getSecret())
        .build());
  }
}
