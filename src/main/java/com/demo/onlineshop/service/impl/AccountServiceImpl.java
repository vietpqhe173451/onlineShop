package com.demo.onlineshop.service.impl;

import com.demo.onlineshop.dto.AccountDTOCreate;
import com.demo.onlineshop.dto.AccountDTOResponse;
import com.demo.onlineshop.dto.LoginDTORequest;
import com.demo.onlineshop.dto.LoginDTOResponse;
import com.demo.onlineshop.entity.Account;
import com.demo.onlineshop.repository.AccountRepository;
import com.demo.onlineshop.service.AccountService;
import com.demo.onlineshop.mapper.AccountMapper;
import com.demo.onlineshop.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    @Override
    public AccountDTOResponse createAccount(AccountDTOCreate accountDTOCreate) {
        Account account = AccountMapper.toAccount(accountDTOCreate);
        account.setPassword(passwordEncoder.encode(accountDTOCreate.getPassword()));
        account = accountRepository.save(account);
        return AccountMapper.toAccountDTOResponse(account);
    }

//    @Override
//    public LoginDTOResponse login(LoginDTORequest loginDTORequest) {
//        Account account = accountRepository.findByUsername(loginDTORequest.getUsername())
//                .orElseThrow(() -> new RuntimeException("Username does not exist"));
//        boolean isChecked = passwordEncoder.matches(loginDTORequest.getPassword(),account.getPassword());
//        if(!isChecked){
//            throw new RuntimeException("Username or password incorrect!");
//        }
//        long ONE_DAY = 24 * 60 * 60;
//        String accessToken = jwtTokenUtil.generateJwtToken(AccountMapper.toTokenPayLoad(account),ONE_DAY);
//        return LoginDTOResponse.builder()
//                .accessToken(accessToken)
//                .accountDTOResponse(AccountMapper.toAccountDTOResponse(account))
//                .build();
//    }

    @Override
    public LoginDTOResponse loginAccount(LoginDTORequest loginDTORequest) {
        //Lay account theo username
        Account account = accountRepository.findByUsername(loginDTORequest.getUsername()).orElseThrow(
                ()->new RuntimeException("User does not exist"));
        //kiem tra password
        boolean isAuthentication = passwordEncoder.matches(loginDTORequest.getPassword(), account.getPassword());
        if(!isAuthentication){
            throw new RuntimeException("Username of password is incorrect");
        }
        //ok->gen token
        final int ONE_DAY_SECONDS = 60*60*24;
        String accessToken = jwtTokenUtil.generateToken(AccountMapper.toTokenPayLoad(account), ONE_DAY_SECONDS);
        //tra ve nguoi dung
        return LoginDTOResponse.builder()
                .accessToken(accessToken)
                .accountDTOResponse(AccountMapper.toAccountDTOResponse(account))
                .build();
    }
}
