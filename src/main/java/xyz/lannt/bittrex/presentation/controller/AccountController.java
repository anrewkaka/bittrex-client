package xyz.lannt.bittrex.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.lannt.bittrex.application.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @RequestMapping(value = "/balance", method = RequestMethod.GET)
  public ResponseEntity<?> getBalance() {
    return ResponseEntity.ok(accountService.getBalances());
  }

  @RequestMapping(value = "/balance/{currency}", method = RequestMethod.GET)
  public ResponseEntity<?> getBalance(@PathVariable String currency) {
    return ResponseEntity.ok(accountService.getBalance(currency));
  }
}
