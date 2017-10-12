package xyz.lannt.market.response.bittrex;

import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

public class BittrexOrderHistoryResponse extends BittrexResponse {

  public BittrexOrderHistoryResponse(Boolean success, String message, List<LinkedTreeMap<String, Object>> result) {
    super(success, message, result);
  }

}
