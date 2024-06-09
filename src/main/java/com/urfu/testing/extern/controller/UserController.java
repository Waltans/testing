package com.urfu.testing.extern.controller;

import com.urfu.testing.application.services.UserService;
import com.urfu.testing.domain.User;
import com.urfu.testing.extern.assambler.UserAssembler;
import com.urfu.testing.extern.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final UserAssembler userAssembler;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = userAssembler.toModel(userDto);
        return ResponseEntity.ok(this.userService.saveUser(user));
    }
}
