package com.lmfun.mapper.menu;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.lmfun.pojo.po.menu.PermissionPO;

@Mapper
public interface PermissionMapper {

    List<PermissionPO> listPermissionByPermissionKeys(Set<String> permissionKeys);

}
