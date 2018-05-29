package com.lmfun.mapper.account;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lmfun.pojo.po.account.UserRolePO;

@Mapper
public interface UserRoleMapper {

    List<UserRolePO> listUserRole(@Param("userUid")String userUid);

}
