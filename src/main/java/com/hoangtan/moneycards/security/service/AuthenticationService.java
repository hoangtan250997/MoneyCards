package com.hoangtan.moneycards.security.service;

import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.exception.AuthorizationException;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.InputValidationException;
import com.hoangtan.moneycards.security.resource.model.JwtRequest;
import com.hoangtan.moneycards.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Base64;

@Stateless
public class AuthenticationService {

    @Inject
    private UserService userService;

    public boolean checkAuthentication(JwtRequest jwtRequest) throws InputValidationException, AuthorizationException {
        User user = userService.getEntityByEmail(jwtRequest.getEmail());
        String password = decryptBase64Password(jwtRequest.getPassword());
        return BCrypt.checkpw(password, user.getPassword());
    }

    private String decryptBase64Password(String password) throws InputValidationException {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(password);
            return new String(decodedBytes);
        } catch (IllegalArgumentException e) {
            throw new InputValidationException(ErrorMessage.KEY_PASSWORD_NOT_ENCODED, ErrorMessage.PASSWORD_NOT_ENCODED);
        }
    }

}
