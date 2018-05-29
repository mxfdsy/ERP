package com.lmfun.mapper.menu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lmfun.pojo.po.menu.MenuPO;

@Mapper
public interface MenuMapper {

    List<MenuPO> listMenuByIds(@Param("menuIds")List<Integer> menuIds);

}
