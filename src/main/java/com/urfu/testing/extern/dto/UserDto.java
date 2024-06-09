package com.urfu.testing.extern.dto;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto {

    private UUID uuid;

    private String username;

    private String password;

}
