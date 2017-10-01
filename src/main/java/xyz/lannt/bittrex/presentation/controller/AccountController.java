package xyz.lannt.bittrex.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.lannt.bittrex.application.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @RequestMapping("/balance")
  public ResponseEntity<?> getBalance() {
    return ResponseEntity.ok(accountService.getBalances());
  }
}
