package com.techking.portal.security.service.impl.roleService;

import com.techking.portal.security.entity.Permission;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class RolePermissionTreeDto extends Permission {

    private boolean hasPermission;
    private List<RolePermissionTreeDto> children;
}
