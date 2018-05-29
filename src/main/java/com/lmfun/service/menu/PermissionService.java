package com.lmfun.service.menu;

import java.util.List;
import java.util.Set;

import com.lmfun.pojo.po.menu.PermissionPO;

public interface PermissionService {

    List<PermissionPO> listPermissionByPermissionKeys(Set<String> permissionKeys);

}
