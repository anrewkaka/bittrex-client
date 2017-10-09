package xyz.lannt.bittrex.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import xyz.lannt.bittrex.domain.vo.CryptoValue;

@AllArgsConstructor
public class PriceOfTime {

  private LocalDateTime datetime;
  
  private CryptoValue price;
}
