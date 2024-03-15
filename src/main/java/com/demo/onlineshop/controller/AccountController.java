package com.demo.onlineshop.controller;

import com.demo.onlineshop.dto.AccountDTOCreate;
import com.demo.onlineshop.dto.AccountDTOResponse;
import com.demo.onlineshop.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts")
    public AccountDTOResponse createAccount(@RequestBody AccountDTOCreate accountDTOCreate){
        return accountService.createAccount(accountDTOCreate);
    }
}
