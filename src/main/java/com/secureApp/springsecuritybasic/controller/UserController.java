package com.secureApp.springsecuritybasic.controller;

import com.secureApp.springsecuritybasic.config.UserService;
import com.secureApp.springsecuritybasic.model.dto.ApiResponse;
import com.secureApp.springsecuritybasic.model.dto.UserDTO;
import com.secureApp.springsecuritybasic.model.dto.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ApiResponse<UserResponseDTO> registerUser(@RequestBody UserDTO userDTO){
        UserResponseDTO userResponseDTO = userService.registerUser(userDTO);
        return ApiResponse.<UserResponseDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("user created successfully")
                .result(userResponseDTO)
                .build();
    }



}
