package com.lmfun.service.account;

import java.util.List;

import com.lmfun.pojo.dto.user.LoginRoleDTO;

public interface UserRoleService {

    List<LoginRoleDTO> listUserRoleInfo(String userUid);

}
