package com.lmfun.service.account;

import java.util.List;
import java.util.Set;

public interface UserPermissionService {

    Set<String> listUserRolePermissionKey(List<String> roleUids);

}
