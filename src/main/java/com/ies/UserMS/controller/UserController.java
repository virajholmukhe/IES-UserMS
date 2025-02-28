package com.ies.UserMS.controller;

import com.ies.UserMS.dto.LoginRequest;
import com.ies.UserMS.dto.PasswordChangeRequest;
import com.ies.UserMS.dto.TokenResponse;
import com.ies.UserMS.dto.UserDTO;
import com.ies.UserMS.exception.UserMSException;
import com.ies.UserMS.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<Boolean> createUser(@RequestBody @Valid UserDTO userDTO) throws UserMSException {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginRequest request) throws UserMSException {
        return new ResponseEntity<>(userService.generateToken(request.getEmail(), request.getPassword()), HttpStatus.OK);
    }

    @GetMapping(value = "/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token){
        return new ResponseEntity<>(userService.validateToken(token), HttpStatus.OK);
    }

    @GetMapping(value = "recover-password/{email}")
    public ResponseEntity<Boolean> recoverPassword(@PathVariable String email) throws Exception {
        return new ResponseEntity<>(userService.recoverPassword(email), HttpStatus.OK);
    }

    @PostMapping(value = "/update-password")
    public ResponseEntity<Boolean> updatePassword(@RequestBody PasswordChangeRequest request) throws UserMSException {
        return new ResponseEntity<>(userService.updatePassword(request), HttpStatus.OK);
    }
}
