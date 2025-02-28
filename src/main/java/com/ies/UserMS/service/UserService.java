package com.ies.UserMS.service;

import com.ies.UserMS.dto.*;
import com.ies.UserMS.exception.UserMSException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public boolean createUser(UserDTO userDTO) throws UserMSException;
    public TokenResponse generateToken(String email, String password) throws UserMSException;
    public boolean validateToken(String token);

    public Boolean updatePassword(PasswordChangeRequest passwordChangeRequest) throws UserMSException;
    public Boolean recoverPassword(String email) throws Exception;
}
