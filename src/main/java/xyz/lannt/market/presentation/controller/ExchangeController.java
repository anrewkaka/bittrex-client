package xyz.lannt.market.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.lannt.market.application.service.ExchangeService;
import xyz.lannt.market.domain.model.CurrencyPrice;
import xyz.lannt.market.presentation.dto.CurrencyPriceRegistrationDto;
import xyz.lannt.market.presentation.dto.CurrencySellDto;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {

  @Autowired
  private ExchangeService exchangeService;

  @RequestMapping(value = "/price/{exchange}/{currency}", method = RequestMethod.GET)
  public ResponseEntity<?> getPrice(@PathVariable String exchange, @PathVariable String currency) {
    return ResponseEntity.ok(exchangeService.getPrice(exchange, currency));
  }

  @RequestMapping(value = "/price/{exchange}/{currency}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deletePrice(@PathVariable String exchange, @PathVariable String currency) {
    exchangeService.deletePrice(exchange, currency);
    return ResponseEntity.ok().build();
  }

  @RequestMapping(value = "/price/{exchange}", method = RequestMethod.GET)
  public ResponseEntity<?> getPrices(@PathVariable String exchange) {
    return ResponseEntity.ok(exchangeService.getPrices(exchange));
  }

  @RequestMapping(value = "/price/{exchange}", method = RequestMethod.PUT)
  public ResponseEntity<?> setPrice(@PathVariable String exchange, @RequestBody CurrencyPriceRegistrationDto price) {
    exchangeService.setPrice(exchange, CurrencyPrice.fromDto(price));
    return ResponseEntity.ok().build();
  }

  @RequestMapping(value = "/{exchange}/sell", method = RequestMethod.POST)
  public ResponseEntity<?> sell(@PathVariable String exchange, @RequestBody CurrencySellDto dto) {
    return ResponseEntity.ok(exchangeService.sell(dto));
  }
}
