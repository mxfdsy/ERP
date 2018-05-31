package com.lmfun.mapper.menu;

import java.util.List;
import java.util.Map;

import com.lmfun.pojo.vo.menu.ChildMenu;
import com.lmfun.pojo.vo.menu.MenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lmfun.pojo.po.menu.MenuPO;

@Mapper
public interface MenuMapper {

    List<MenuPO> listMenuByIds(@Param("menuIds")List<Integer> menuIds);

    List<MenuVO> listParentMenu();

    List<ChildMenu> getChild(Integer id);


    List<ChildMenu> listChildMenu(@Param("parentids") List<Integer> parentIds);
}
