package xyz.lannt.bittrex.presentation.dto;

import lombok.Builder;
import lombok.Data;
import xyz.lannt.bittrex.domain.vo.CryptoValue;

@Data
@Builder
public class OrderHistoryDto {

  private String id;

  private String exchange;

  private String date;

  private String type;

  private CryptoValue limit;

  private CryptoValue quantity;

  private CryptoValue quantityRemain;

  private CryptoValue commission;

  private CryptoValue price;

  private CryptoValue pricePerUnit;

  private Boolean isConditional;

  private String condition;

  private String conditionTarget;

  private Boolean immediateOrCancel;
}
