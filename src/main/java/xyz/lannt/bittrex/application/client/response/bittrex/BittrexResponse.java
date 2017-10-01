package xyz.lannt.bittrex.application.client.response.bittrex;

import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

import lombok.AllArgsConstructor;
import xyz.lannt.bittrex.application.client.response.MarketResponse;

@AllArgsConstructor
public class BittrexResponse implements MarketResponse {

  public Boolean success;

  public String message;

  public List<LinkedTreeMap<String, Object>> result;
}
