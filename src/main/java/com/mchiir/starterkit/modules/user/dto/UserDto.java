package com.mchiir.starterkit.modules.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String fullName;
    private String role;
    private boolean enabled;
}