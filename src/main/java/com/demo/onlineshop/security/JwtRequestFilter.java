package com.demo.onlineshop.security;

import com.demo.onlineshop.entity.Account;
import com.demo.onlineshop.mapper.AccountMapper;
import com.demo.onlineshop.model.TokenPayLoad;
import com.demo.onlineshop.repository.AccountRepository;
import com.demo.onlineshop.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtRequestFilter extends OncePerRequestFilter {
    JwtTokenUtil jwtTokenUtil;
    AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");

        String token = null;
        TokenPayLoad tokenPayLoad = null;

        if(requestTokenHeader!=null&&requestTokenHeader.startsWith("Bearer ")){
            token = requestTokenHeader.split("")[1];
            try {
                tokenPayLoad = jwtTokenUtil.getTokenPayLoad(token);
            }catch (ExpiredJwtException e){
                System.out.println("Token is expired");
            }
        }else{
            System.out.println("JWT not start with Bearer");
        }
        if(tokenPayLoad!=null&& SecurityContextHolder.getContext()==null){
            Optional<Account> accountOptional = accountRepository.findById(tokenPayLoad.getAccountId());
            if(accountOptional.isPresent()){
                Account account = accountOptional.get();
                if(jwtTokenUtil.isValid(token, AccountMapper.toTokenPayLoad(account))){
                    //luu vao context holder
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
