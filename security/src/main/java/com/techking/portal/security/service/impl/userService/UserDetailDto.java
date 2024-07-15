package com.techking.portal.security.service.impl.userService;

import com.techking.portal.security.entity.Role;
import com.techking.portal.security.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserDetailDto extends User {

    private List<Role> roles;
}
