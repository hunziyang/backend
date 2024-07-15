package com.techking.portal.security.service.impl.roleService;

import com.techking.portal.security.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoleDetailDto {

    private Role role;
    private List<RolePermissionTreeDto> rolePermissionTreeDtos;
}
