package xyz.lannt.market.response.bittrex;

import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

public class BittrexMarketSummariesResponse extends BittrexResponse {

  public BittrexMarketSummariesResponse(Boolean success, String message, List<LinkedTreeMap<String, Object>> result) {
    super(success, message, result);
  }

}
