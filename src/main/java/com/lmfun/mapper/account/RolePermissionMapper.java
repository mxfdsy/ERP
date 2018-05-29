package com.lmfun.mapper.account;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RolePermissionMapper {

    Set<String> listUserRolePermissionKey(@Param("roleUids")List<String> roleUids);

}
