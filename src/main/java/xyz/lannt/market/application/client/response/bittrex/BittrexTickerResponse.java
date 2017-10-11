package xyz.lannt.market.application.client.response.bittrex;

import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

public class BittrexTickerResponse extends BittrexResponse {

  public BittrexTickerResponse(Boolean success, String message, List<LinkedTreeMap<String, Object>> result) {
    super(success, message, result);
  }

}
