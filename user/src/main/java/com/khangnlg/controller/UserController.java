package com.khangnlg.controller;

import com.khangnlg.exception.ObjectNotValidException;
import com.khangnlg.message.ResponseMessage;
import com.khangnlg.model.UserLoginModel;
import com.khangnlg.model.UserRegistrationModel;
import com.khangnlg.models.Token;
import com.khangnlg.models.UserModel;
import com.khangnlg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/authentication")
    public ResponseMessage userAuthentication(@RequestBody UserLoginModel userLoginModel){
        try {
            Token token = userService.validateUser(userLoginModel);
            return ResponseMessage
                    .builder()
                    .status(HttpStatus.OK.value())
                    .data(token)
                    .build();

        } catch (ObjectNotValidException e) {
            return ResponseMessage
                    .builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
        }
    }

    @PostMapping
    public ResponseMessage createUser(@RequestBody UserRegistrationModel userRegistrationModel){
        try {
            Token token = userService.createUser(userRegistrationModel);
            return ResponseMessage
                    .builder()
                    .status(HttpStatus.CREATED.value())
                    .data(token)
                    .build();
        } catch (Exception e) {
            return ResponseMessage
                    .builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
        }
    }

    @GetMapping("/me")
    public ResponseMessage getMyInformation(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserModel userModel = userService.getUserByUsername(userDetails.getUsername());
        if(userModel!=null){
            return ResponseMessage
                    .builder()
                    .status(HttpStatus.OK.value())
                    .data(userModel)
                    .build();
        }else {
            return ResponseMessage
                    .builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("Can't find user")
                    .build();
        }
    }



}
