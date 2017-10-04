package xyz.lannt.bittrex.domain.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarketNames extends ArrayList<String> {

  private static final long serialVersionUID = 2727352985001875621L;

  public MarketNames() {
    super();
  }

  public MarketNames(String[] markets) {
    super();
    addAll(Arrays.asList(markets));
  }

  public MarketNames(List<String> markets) {
    super();
    addAll(markets);
  }
}
