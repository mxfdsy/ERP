package com.lmfun.mapper.account;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lmfun.pojo.po.account.UserAccountPO;

@Mapper
public interface UserAccountMapper {

    UserAccountPO getUserAccountByLoginNameAndPassword(@Param("loginName")String loginName, @Param("password")String password);

}
