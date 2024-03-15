package com.demo.onlineshop.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class AccountDTOResponse {
    int id;
    String username;
    String email;
}
