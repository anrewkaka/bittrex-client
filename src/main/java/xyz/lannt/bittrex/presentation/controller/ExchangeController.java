package xyz.lannt.bittrex.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.lannt.bittrex.application.service.ExchangeService;
import xyz.lannt.bittrex.domain.vo.CryptoValue;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {

  @Autowired
  private ExchangeService exchangeService;

  @RequestMapping("/price/{exchange}/{currency}/{price}")
  public ResponseEntity<?> setPrice(String exchange, String currency, String price) {
    exchangeService.setPrice(exchange, currency, CryptoValue.create(price));
    return ResponseEntity.ok().build();
  }
}
