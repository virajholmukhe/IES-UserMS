package com.ies.UserMS.service;

import com.ies.UserMS.dto.*;
import com.ies.UserMS.entity.UserEntity;
import com.ies.UserMS.exception.ExceptionConstants;
import com.ies.UserMS.exception.UserMSException;
import com.ies.UserMS.repository.UserRepository;
import com.ies.UserMS.utils.EmailUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Autowired
    EmailUtils emailUtils;

    @Override
    public boolean createUser(UserDTO userDTO) throws UserMSException {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userDTO.getEmail());
        if(userEntityOptional.isPresent()){
            throw new UserMSException(ExceptionConstants.EMAIL_ALREADY_USED.toString());
        }
        try {
            String tempPassword = generateTempPassword();
            userDTO.setPassword(passwordEncoder.encode(tempPassword));
            userDTO.setPasswordChanged(false);
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userDTO, userEntity);
            UserEntity userEntityResponse = userRepository.save(userEntity);
            return emailUtils.sendMailToSendTempPassword(userDTO.getEmail(),  tempPassword);
        } catch (Exception e) {
            throw new UserMSException(ExceptionConstants.USER_IS_NOT_CREATED.toString());
        }
    }

    @Override
    public String generateToken(String email, String password) throws UserMSException {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        if(userEntityOptional.isPresent()){
            if(userEntityOptional.get().isPasswordChanged()){
                if(passwordEncoder.matches(password, userEntityOptional.get().getPassword())){
                    return jwtService.generateToken(email);
                }else{
                    throw new UserMSException(ExceptionConstants.INVALID_PASSWORD.toString());
                }
            }else{
                throw new UserMSException(ExceptionConstants.PASSWORD_NOT_AVAILABLE.toString());
            }

        }else{
            throw new UserMSException(ExceptionConstants.EMAIL_NOT_EXIST.toString());
        }
    }

    @Override
    public boolean validateToken(String token) {
        try {
            jwtService.validateToken(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean updatePassword(PasswordChangeRequest passwordChangeRequest) throws UserMSException {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(passwordChangeRequest.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            if(passwordEncoder.matches(passwordChangeRequest.getTempPassword(), userEntity.getPassword())){
                userEntity.setPassword(passwordEncoder.encode(passwordChangeRequest.getPassword()));
                userEntity.setPasswordChanged(true);
                userRepository.save(userEntity);
                return true;
            }else{
                throw new UserMSException(ExceptionConstants.INVALID_TEMP_PASSWORD.toString());
            }
        }else{
            throw new UserMSException(ExceptionConstants.EMAIL_NOT_EXIST.toString());
        }
    }

    @Override
    public Boolean recoverPassword(String email) throws Exception {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        if(userEntityOptional.isPresent()){
            String tempPassword = generateTempPassword();
            userEntityOptional.get().setPassword(passwordEncoder.encode(tempPassword));
            userEntityOptional.get().setPasswordChanged(false);
            userRepository.save(userEntityOptional.get());
            return emailUtils.sendMailToRecoverPassword(email, tempPassword);
        }else {
            throw new UserMSException(ExceptionConstants.EMAIL_NOT_EXIST.toString());
        }
    }

    public String generateTempPassword(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }
}
