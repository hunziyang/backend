package com.techking.portal.security.service;

import com.techking.portal.core.utils.pagination.PagedList;
import com.techking.portal.security.entity.Role;
import com.techking.portal.security.service.impl.roleService.*;

import java.util.List;

public interface RoleService {

    void insert(RoleVo roleVo);

    void update(Long id, RoleUpdateVo roleUpdateVo);

    void delete(Long id);

    PagedList<Role> select(RoleSelectVo roleSelectVo);

    RoleDetailDto detail(Long id);

    List<Role> selectAll();
}
