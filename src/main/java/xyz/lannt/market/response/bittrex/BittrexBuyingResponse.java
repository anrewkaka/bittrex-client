package xyz.lannt.market.response.bittrex;

import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

public class BittrexBuyingResponse extends BittrexResponse {

  public BittrexBuyingResponse(Boolean success, String message, List<LinkedTreeMap<String, Object>> result) {
    super(success, message, result);
  }

}
