package com.techking.portal.security.service.impl.permissionService;

import com.techking.portal.security.entity.Permission;
import lombok.Data;

import java.util.List;

@Data
public class PermissionTreeDto extends Permission {
    private List<PermissionTreeDto> children;
}
