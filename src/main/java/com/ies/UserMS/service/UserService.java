package com.ies.UserMS.service;

import com.ies.UserMS.dto.LoginRequest;
import com.ies.UserMS.dto.LoginResponse;
import com.ies.UserMS.dto.PasswordChangeRequest;
import com.ies.UserMS.dto.UserDTO;
import com.ies.UserMS.exception.UserMSException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public boolean createUser(UserDTO userDTO) throws UserMSException;
    public String generateToken(String email, String password) throws UserMSException;
    public boolean validateToken(String token);

    public Boolean updatePassword(PasswordChangeRequest passwordChangeRequest) throws UserMSException;
    public Boolean recoverPassword(String email) throws Exception;
}
