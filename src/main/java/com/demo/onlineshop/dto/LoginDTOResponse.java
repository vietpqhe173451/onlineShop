package com.demo.onlineshop.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class LoginDTOResponse {
    String accessToken;
    AccountDTOResponse accountDTOResponse;
}
