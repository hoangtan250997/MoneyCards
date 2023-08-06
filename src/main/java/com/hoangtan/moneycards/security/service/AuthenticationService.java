package com.hoangtan.moneycards.security.service;

import com.axonactive.agileskills.base.exception.AuthorizationException;
import com.axonactive.agileskills.base.exception.ErrorMessage;
import com.axonactive.agileskills.base.exception.InputValidationException;
import com.axonactive.agileskills.base.security.controller.model.JwtRequest;
import com.axonactive.agileskills.user.entity.UserEntity;
import com.axonactive.agileskills.user.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Base64;

@Stateless
public class AuthenticationService {

    @Inject
    private UserService userService;

    public boolean checkAuthentication(JwtRequest jwtRequest) throws InputValidationException, AuthorizationException {
        UserEntity user = userService.getEntityByEmail(jwtRequest.getEmail());
        String password = decryptBase64Password(jwtRequest.getPassword());
        return BCrypt.checkpw(password, user.getPassword());
    }

    private String decryptBase64Password(String password) throws InputValidationException {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(password);
            return new String(decodedBytes);
        } catch (IllegalArgumentException e) {
            throw new InputValidationException(ErrorMessage.KEY_PASS_EMAIL_INVALID, ErrorMessage.PASS_EMAIL_INVALID);
        }
    }

}
