package com.hoangtan.moneycards.security.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hoangtan.moneycards.config.AppConfigService;
import com.hoangtan.moneycards.entity.RoleEnum;
import com.hoangtan.moneycards.entity.StatusEnum;
import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.exception.AuthorizationException;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.InputValidationException;
import com.hoangtan.moneycards.security.resource.model.JwtRequest;
import com.hoangtan.moneycards.security.service.AuthenticationService;
import com.hoangtan.moneycards.security.service.dto.JwtResponse;
import com.hoangtan.moneycards.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Stateless
public class JwtUtils {
    private static final String EMAIL = "EMAIL";
    private static final String ROLE = "ROLE";
    private static final String BEARER = "Bearer";
    private static final Validator validator =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private UserService userService;

    public String generateToken(JwtRequest jwtRequest) throws AuthorizationException, InputValidationException {
        if (!authenticationService.checkAuthentication(jwtRequest)) {
            throw new InputValidationException(ErrorMessage.KEY_PASS_EMAIL_INVALID, ErrorMessage.PASS_EMAIL_INVALID);
        }
        String token;
        String secretKey = AppConfigService.getSecretKey();
        String issuer = AppConfigService.getIssuer();
        int liveTime = AppConfigService.getTimeToLive();
        RoleEnum roleEnum = userService.getEntityByEmail(jwtRequest.getEmail()).getRole();

        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            token = JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withJWTId(UUID.randomUUID().toString())
                    .withClaim(EMAIL, jwtRequest.getEmail())
                    .withClaim(ROLE, String.valueOf(roleEnum))
                    .withExpiresAt(new Date(System.currentTimeMillis() + liveTime))
                    .sign(algorithm);
        } catch (JWTCreationException | IllegalArgumentException e) {
            throw new AuthorizationException(Response.Status.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS);
        }
        return token;
    }

    public void validateJwtToken(String token) throws AuthorizationException {
        if (token == null) {
            throw new AuthorizationException(Response.Status.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS);
        }
        try {
            String secretKey = AppConfigService.getSecretKey();
            String issuer = AppConfigService.getIssuer();
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer).build();
            verifier.verify(token.substring(BEARER.length()).trim());
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new AuthorizationException(Response.Status.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS);
        }
    }

    public JwtResponse generateJwtResponse(JwtRequest jwtRequest) throws AuthorizationException, InputValidationException {
        verifyJwtRequest(jwtRequest);
        String email = jwtRequest.getEmail().trim();
        String token = generateToken(jwtRequest);
        User user = userService.getEntityByEmail(email);
        RoleEnum roleEnum = user.getRole();
        StatusEnum status = user.getStatus();
        return new JwtResponse(token, email, roleEnum, status);
    }

    public RoleEnum getRoleFromToken(String authorization) {
        String token = authorization.substring(BEARER.length()).trim();
        DecodedJWT decodedJWT = JWT.decode(token);
        return RoleEnum.valueOf(decodedJWT.getClaim(ROLE).asString());
    }

    public String getEmailFromToken(String authorization) {
        String token = authorization.substring(BEARER.length()).trim();
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim(EMAIL).asString();
    }

    public Date getExpireTokenTime(String authorization) {
        String token = authorization.substring(BEARER.length()).trim();
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getExpiresAt();
    }

    private boolean isActive(StatusEnum status) {
        return status.equals(StatusEnum.ACTIVE);
    }

    public void verifyJwtRequest(JwtRequest jwtRequest) throws InputValidationException, AuthorizationException {
        Set<ConstraintViolation<JwtRequest>> violations = validator.validate(jwtRequest);

        if (CollectionUtils.isNotEmpty(violations)) {
            throw new ConstraintViolationException(violations);
        }

        StatusEnum statusEnum = userService.getEntityByEmail(jwtRequest.getEmail()).getStatus();
        if (!isActive(statusEnum)) {
            throw new AuthorizationException(Response.Status.FORBIDDEN, ErrorMessage.KEY_FORBIDDEN_ACCESS, ErrorMessage.FORBIDDEN_ACCESS);
        }
    }
}
