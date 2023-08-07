package com.hoangtan.moneycards.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoangtan.moneycards.entity.RoleEnum;
import com.hoangtan.moneycards.entity.StatusEnum;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import static com.hoangtan.moneycards.exception.ErrorMessage.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;

    @Size(max = 255, message = USER_NAME_LENGTH_CONSTRAINT)
    private String name;

    @NotBlank(message = EMAIL_BLANK_OR_NULL)
    @Email(message = EMAIL_WRONG_FORMAT)
    private String email;

    @NotBlank(message = PASSWORD_BLANK_OR_NULL)
    private String password;

    private StatusEnum status;

    private RoleEnum role;
}
