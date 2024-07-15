package com.techking.portal.security.service.impl.userService;

import com.techking.portal.security.service.impl.permissionService.PermissionTreeDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true)
public class LoginDto {

    private Long id;
    private String username;
    private String jwt;
    private Set<String> permissionCodes;
    private List<PermissionTreeDto> menus;
}
