package com.codingShuttle.SpringSecurity1.controller;

import com.codingShuttle.SpringSecurity1.dto.LoginDto;
import com.codingShuttle.SpringSecurity1.dto.SignUpDto;
import com.codingShuttle.SpringSecurity1.dto.UserDto;
import com.codingShuttle.SpringSecurity1.services.UserService;
import com.codingShuttle.SpringSecurity1.services.impl.AuthServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {

    Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private final UserService userService;

    @Autowired
    private final AuthServiceImpl authService;

//    @GetMapping
//    private List<UserDto> getAllUsers(){
//        log.info("Controller getAllUsers");
//        return userService.getAllUsers();
//    }

    //    @GetMapping(path = "/{userId}")
//    public UserDto getUserById(@PathVariable("userId") Long userId){
//        log.info("Controller getUserById");
//        return userService.getUserById(userId);
//    }
//
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        log.info("Controller signUp");
        UserDto userDto = userService.signUp(signUpDto);
        log.info("signUp userDto={}", userDto);
        log.info("signUp Complete");
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        log.info("Controller login");
        String token = authService.login(loginDto);
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        log.info("Login token={}", token);
        log.info("login Complete");
        return ResponseEntity.ok(token);

    }

}
