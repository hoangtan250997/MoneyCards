package com.hoangtan.moneycards.security.resource.model;

import com.hoangtan.moneycards.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Principal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserPrincipal implements Principal {
    private String email;
    private RoleEnum roleEnum;

    @Override
    public String getName() {
        return email;
    }
}
