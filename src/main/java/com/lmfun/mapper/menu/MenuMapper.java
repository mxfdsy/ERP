package com.lmfun.mapper.menu;

import com.lmfun.pojo.po.menu.MenuPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuPO> listMenuByIds(@Param("menuIds")List<Integer> menuIds);

    List<MenuPO> listParentMenu();

    List<ChildMenu> getChild(Integer id);


    List<MenuPO> listChildMenu(@Param("parentids") List<Integer> parentIds);
}
