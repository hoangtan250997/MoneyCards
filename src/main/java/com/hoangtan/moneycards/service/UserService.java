package com.hoangtan.moneycards.service;


import com.hoangtan.moneycards.dao.UserDAO;
import com.hoangtan.moneycards.entity.RoleEnum;
import com.hoangtan.moneycards.entity.StatusEnum;
import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.exception.InputValidationException;
import com.hoangtan.moneycards.service.mapper.UserMapper;
import com.hoangtan.moneycards.service.model.UserDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.Set;

@Stateless
public class UserService {

    private static final Validator validator =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();
    @Inject
    private UserDAO userDAO;

    @Inject
    private UserMapper userMapper;

    public UserDTO create(UserDTO user) throws InputValidationException, IllegalArgumentException {
        verifyUser(user);

        User userEntity = User.builder()
                .username(user.getName().trim())
                .email(user.getEmail())
                .password(BCrypt.hashpw(decodePassword(user.getPassword()), BCrypt.gensalt()))
                .status(StatusEnum.ACTIVE)
                .roleEnum(RoleEnum.ROLE_USER)
                .build();
        return userMapper.toDTO(userDAO.create(userEntity));
    }

    public UserEntity getEntityByEmail(String email) throws AuthorizationException {
        return userDAO.findByEmail(email)
                .orElseThrow(() -> new AuthorizationException(Response.Status.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS));
    }

    public void verifyUser(UserDTO user) throws InputValidationException {
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
        if (CollectionUtils.isNotEmpty(violations)) {
            throw new ConstraintViolationException(violations);
        }
        if (isUserExisted(user.getEmail())) {
            throw new InputValidationException(ErrorMessage.KEY_USER_ALREADY_EXISTED,
                    ErrorMessage.USER_ALREADY_EXISTED);
        }
        if (isNameNullOrEmpty(user.getName())) {
            user.setName(user.getEmail().split("@")[0]);
        }
        if (!isPasswordMatchPattern(user.getPassword())) {
            throw new InputValidationException(ErrorMessage.KEY_PASSWORD_NOT_MATCH_PATTERN,
                    ErrorMessage.PASSWORD_NOT_MATCH_PATTERN);
        }
    }

    private boolean isNameNullOrEmpty(String name) {
        return name == null || name.trim().equals("");
    }

    private boolean isUserExisted(String email) {
        return userDAO.findByEmail(email.trim().toLowerCase()).isPresent();
    }

    private boolean isPasswordMatchPattern(String password) throws InputValidationException {
        String pattern = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,}$";
        return decodePassword(password).matches(pattern);
    }

    private String decodePassword(String password) throws InputValidationException {
        try {
            byte[] decoded = Base64.getDecoder().decode(password);
            return (new String(decoded));
        } catch (IllegalArgumentException e) {
            throw new InputValidationException(ErrorMessage.KEY_PASSWORD_NOT_ENCODED,
                    ErrorMessage.PASSWORD_NOT_ENCODED);
        }
    }
}
