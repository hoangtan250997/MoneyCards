package com.hoangtan.moneycards.security.service.dto;

import com.hoangtan.moneycards.entity.Role;
import com.hoangtan.moneycards.entity.StatusEnum;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private String token;
    private String email;

    private Role role;

    private String type = "Bearer";
    private StatusEnum status;

    public JwtResponse(String token, String email, Role role, StatusEnum status) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.status = status;
    }
}
