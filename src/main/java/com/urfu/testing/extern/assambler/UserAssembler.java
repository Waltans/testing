package com.urfu.testing.extern.assambler;

import com.urfu.testing.domain.User;
import com.urfu.testing.extern.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {


    public User toModel(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .uuid(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
