package com.hoangtan.moneycards.service.model;

import com.hoangtan.moneycards.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleAssignmentDTO {
    private Integer userId;
    private RoleEnum role;

}
