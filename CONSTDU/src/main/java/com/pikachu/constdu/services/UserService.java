package com.pikachu.constdu.services;

import com.pikachu.constdu.dto.SignInResponseDto;
import com.pikachu.constdu.dto.SignUpResponseDto;
import com.pikachu.constdu.dto.SignUpDto;
import com.pikachu.constdu.exceptions.CustomException;
import com.pikachu.constdu.models.User;
import com.pikachu.constdu.repositories.RoleRepository;
import com.pikachu.constdu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    @Lazy
    UserRepository userRepositories;

    @Autowired
    @Lazy
    RoleRepository roleRepositories;

    private final TokenService tokenService;
    public UserService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Transactional
    public SignUpResponseDto signUp(SignUpDto signUpDto){
        //check if user exist;
        if(Objects.nonNull(userRepositories.findByEmail(signUpDto.getEmail()))){
            throw new CustomException("Email already exist!");
        }
        if(Objects.nonNull(userRepositories.findByPhoneNumber(signUpDto.getPhoneNumber()))){
            throw new CustomException("Phone Number already exist!");
        }
        //TODO:hash password
        String encryptedPassword = signUpDto.getPassword();
        //save data
        User tempUser = new User();
        tempUser.setEmail(signUpDto.getEmail());
        tempUser.setPassword(encryptedPassword);
        tempUser.setFirstName(signUpDto.getFirstName());
        tempUser.setLastName(signUpDto.getLastName());
        tempUser.setPhoneNumber(signUpDto.getPhoneNumber());
        tempUser.setRole(roleRepositories.findById(1));
        userRepositories.save(tempUser);
        roleRepositories.findById(1).getUsers().add(tempUser);
        //Free up memory right now
        tempUser = null;
        System.gc();

        //TODO:create token

        SignUpResponseDto signUpResponseDto =  new SignUpResponseDto("200","Success");
        return signUpResponseDto;
    }
    @Transactional
    public SignInResponseDto signIn(Authentication authentication){
//        User tempUser = userRepositories.findByEmail(signInDto.getEmail());
//        if(Objects.isNull(tempUser)){
//            throw new CustomException("User is not exist!");
//        }
//        if(!signInDto.getPassword().equals(tempUser.getPassword())){
//            throw new CustomException("Wrong Password");
//        }
        SignInResponseDto signInResponseDto = new SignInResponseDto("200","Success",tokenService.generateToken(authentication));
        return signInResponseDto;
    }
}
