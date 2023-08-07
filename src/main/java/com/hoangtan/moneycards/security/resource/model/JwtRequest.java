package com.hoangtan.moneycards.security.resource.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.hoangtan.moneycards.exception.ErrorMessage.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {

    @NotBlank(message = EMAIL_BLANK_OR_NULL)
    @Email(message = EMAIL_WRONG_FORMAT)
    private String email;

    @NotBlank(message = PASSWORD_BLANK_OR_NULL)
    private String password;
}
