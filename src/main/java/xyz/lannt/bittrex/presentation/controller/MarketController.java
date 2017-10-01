package xyz.lannt.bittrex.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.lannt.bittrex.application.service.MarketService;

@Controller
@RequestMapping("/market")
public class MarketController {

  @Autowired
  private MarketService marketService;

  @RequestMapping(value = "/summary", method = RequestMethod.GET)
  public ResponseEntity<?> getSummaries(String[] markets) {
    return ResponseEntity.ok(marketService.getSummaries(markets));
  }

  @RequestMapping(value = "/summary/{market}", method = RequestMethod.GET)
  public ResponseEntity<?> getSummary(@PathVariable String market) {
    return ResponseEntity.ok(marketService.getSummary(market));
  }

  @RequestMapping(value = "/compare", method = RequestMethod.GET)
  public ResponseEntity<?> compare(String base, String source, String target) {
    return ResponseEntity.ok(marketService.getCompare(base, source, target));
  }
}
