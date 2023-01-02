package com.pikachu.constdu.apiControllers;

import com.pikachu.constdu.dto.SignInResponseDto;
import com.pikachu.constdu.dto.SignUpResponseDto;
import com.pikachu.constdu.dto.SignUpDto;
import com.pikachu.constdu.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String homeTest(){
        return "Hello Tester";
    }
    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignUpDto user){
        return userService.signUp(user);
    }
    @PostMapping("/signin")
    public SignInResponseDto signIn(Authentication authentication){
        return userService.signIn(authentication);
    }

    @GetMapping("/sendEmail")
    public String sendEmail(){

        return "OK";
    }
}
