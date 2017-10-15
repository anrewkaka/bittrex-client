package xyz.lannt.market.response.bittrex;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.internal.LinkedTreeMap;

public class BittrexResults extends ArrayList<BittrexResult> {

  private static final long serialVersionUID = 105569625867186512L;

  public BittrexResults(List<LinkedTreeMap<String, Object>> result) {
    for (LinkedTreeMap<String, Object> map : result) {
      this.add(new BittrexResult(map));
    }
  }
}
