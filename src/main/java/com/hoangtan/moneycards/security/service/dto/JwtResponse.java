package com.hoangtan.moneycards.security.service.dto;

import com.hoangtan.moneycards.entity.RoleEnum;
import com.hoangtan.moneycards.entity.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private String token;
    private String email;

    private RoleEnum roleEnum;

    private String type = "Bearer";
    private StatusEnum status;

    public JwtResponse(String token, String email, RoleEnum roleEnum, StatusEnum status) {
        this.token = token;
        this.email = email;
        this.roleEnum = roleEnum;
        this.status = status;
    }
}
