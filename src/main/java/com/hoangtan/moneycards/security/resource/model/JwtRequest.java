package com.hoangtan.moneycards.security.resource.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.axonactive.agileskills.base.exception.ErrorMessage.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {

    @NotBlank(message = EMAIL_BLANK_OR_NULL)
    @Email(message = EMAIL_WRONG_FORMAT)
    private String email;

    @ApiModelProperty(example = "encrypted string")
    @NotBlank(message = PASSWORD_BLANK_OR_NULL)
    private String password;
}
