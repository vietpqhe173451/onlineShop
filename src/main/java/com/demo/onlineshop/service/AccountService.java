package com.demo.onlineshop.service;

import com.demo.onlineshop.dto.AccountDTOCreate;
import com.demo.onlineshop.dto.AccountDTOResponse;
import com.demo.onlineshop.dto.LoginDTORequest;
import com.demo.onlineshop.dto.LoginDTOResponse;

public interface AccountService {
    AccountDTOResponse createAccount(AccountDTOCreate accountDTOCreate);

    LoginDTOResponse loginAccount(LoginDTORequest loginDTORequest);
}
