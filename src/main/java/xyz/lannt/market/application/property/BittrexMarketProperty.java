package xyz.lannt.market.application.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "bittrex")
public class BittrexMarketProperty {

  private String baseUrl;

  private String apiKey;

  private String secret;
}
