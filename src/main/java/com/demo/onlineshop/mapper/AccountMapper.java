package com.demo.onlineshop.mapper;

import com.demo.onlineshop.dto.AccountDTOCreate;
import com.demo.onlineshop.dto.AccountDTOResponse;
import com.demo.onlineshop.entity.Account;
import com.demo.onlineshop.model.TokenPayLoad;

public class AccountMapper {
    public static Account toAccount(AccountDTOCreate accountDTOCreate) {
        return Account.builder()
                .username(accountDTOCreate.getUsername())
                .email(accountDTOCreate.getEmail())
                .password(accountDTOCreate.getPassword())
                .build();
    }

    public static AccountDTOResponse toAccountDTOResponse(Account account) {
        return AccountDTOResponse.builder()
                .id(account.getId())
                .username(account.getUsername())
                .email(account.getEmail())
                .build();
    }

    public static TokenPayLoad toTokenPayLoad(Account account) {
        return TokenPayLoad.builder()
                .accountId(account.getId())
                .username(account.getUsername())
                .build();
    }
}
