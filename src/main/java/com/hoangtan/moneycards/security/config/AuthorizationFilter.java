package com.hoangtan.moneycards.security.config;

import com.axonactive.agileskills.base.exception.AuthorizationException;
import com.axonactive.agileskills.base.exception.ErrorMessage;
import com.axonactive.agileskills.base.exception.ResponseBody;
import com.axonactive.agileskills.user.entity.RoleEnum;
import com.hoangtan.moneycards.security.utility.JwtUtils;
import com.hoangtan.moneycards.security.resource.model.RequestSecurityContext;
import com.hoangtan.moneycards.security.resource.model.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.util.Date;
import java.util.UUID;

@Provider
public class AuthorizationFilter implements ContainerRequestFilter {

    private static final Logger logger = LogManager.getLogger(AuthorizationFilter.class);

    @Context
    private ResourceInfo info;

    @Inject
    private JwtUtils jwtUtils;

    @Override
    public void filter(ContainerRequestContext request) {
        //@DenyAll in class or method: Abort Always
        if (isDenied()) {
            request.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ResponseBody(Response.Status.FORBIDDEN, ErrorMessage.KEY_FORBIDDEN_ACCESS, ErrorMessage.FORBIDDEN_ACCESS))
                    .build());
            return;
        }

        RolesAllowed methodRoles = info.getResourceMethod().getAnnotation(RolesAllowed.class);
        RolesAllowed classRoles = info.getResourceClass().getAnnotation(RolesAllowed.class);

        if (methodRoles == null && classRoles == null) {
            return;
        }

        String authHeader = request.getHeaderString("Authorization");
        if (isNotValidJwt(authHeader)) {
            logger.info("Unauthorized access");
            request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ResponseBody(Response.Status.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS)).build());
            return;
        }

        RoleEnum role = jwtUtils.getRoleFromToken(authHeader);
        String email = jwtUtils.getEmailFromToken(authHeader);
        SecurityContext sc = new RequestSecurityContext(new UserPrincipal(email, role));

        String[] localPart = email.split("@");
        ThreadContext.put("mail", localPart[0] + ":" + UUID.randomUUID().toString().replace("-", "").substring(0, 8));

        request.setSecurityContext(sc);

        //Forbidden when class-level OR method-level roles do not match
        if (hasNoRole(classRoles, request.getSecurityContext())
                || hasNoRole(methodRoles, request.getSecurityContext())) {
            logger.info("Forbidden access");
            request.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ResponseBody(Response.Status.FORBIDDEN, ErrorMessage.KEY_FORBIDDEN_ACCESS, ErrorMessage.FORBIDDEN_ACCESS)).build());
        }
    }

    private boolean isDenied() {
        DenyAll methodDenyAll = info.getResourceMethod().getAnnotation(DenyAll.class);
        DenyAll classDenyAll = info.getResourceClass().getAnnotation(DenyAll.class);
        return methodDenyAll != null || classDenyAll != null;
    }

    private boolean isNotValidJwt(String header) {
        if (header == null) {
            return true;
        }

        try {
            jwtUtils.validateJwtToken(header);
        } catch (AuthorizationException e) {
            return true;
        }

        Date expireTokenTime = jwtUtils.getExpireTokenTime(header);
        return !header.startsWith("Bearer ") && expireTokenTime.before(new Date(System.currentTimeMillis()));
    }

    private boolean hasNoRole(RolesAllowed anno, SecurityContext sc) {
        if (anno == null) {
            return false;
        }

        String[] roleStrings = anno.value();
        for (String roleString : roleStrings) {
            if (sc.isUserInRole(roleString)) {
                return false;
            }
        }
        return true;
    }
}
