package com.hoangtan.moneycards.security.resource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.hoangtan.moneycards.exception.ErrorMessage.EMAIL_BLANK_OR_NULL;
import static com.hoangtan.moneycards.exception.ErrorMessage.EMAIL_WRONG_FORMAT;
import static com.hoangtan.moneycards.exception.ErrorMessage.PASSWORD_BLANK_OR_NULL;

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
