package xyz.lannt.market.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import xyz.lannt.market.domain.vo.CryptoValue;

@AllArgsConstructor
public class PriceOfTime {

  private LocalDateTime datetime;
  
  private CryptoValue price;
}
