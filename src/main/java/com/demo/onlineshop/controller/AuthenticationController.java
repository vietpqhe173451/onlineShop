package com.demo.onlineshop.controller;

import com.demo.onlineshop.dto.LoginDTORequest;
import com.demo.onlineshop.dto.LoginDTOResponse;
import com.demo.onlineshop.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AccountService accountService;

    @PostMapping("/login")
    public LoginDTOResponse loginAccount(@RequestBody LoginDTORequest loginDTORequest){
        return accountService.loginAccount(loginDTORequest);
    }
}
