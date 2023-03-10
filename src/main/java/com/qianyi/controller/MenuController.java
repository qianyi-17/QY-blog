package com.qianyi.controller;

import com.qianyi.domain.ResponseResult;
import com.qianyi.domain.entity.Menu;
import com.qianyi.domain.vo.MenuTreeVo;
import com.qianyi.service.MenuService;
import com.qianyi.utils.SystemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 千亦
 * @create 2022-11-22-16:49
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 查询所有菜单列表
     * @param menu
     * @return
     */
    @GetMapping("/list")
    public ResponseResult menuList(Menu menu){
        ResponseResult result = menuService.menuList(menu);
        return result;
    }

    /**
     * 新增菜单接口
     * @param menu
     * @return
     */
    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu){
        ResponseResult result = menuService.addMenu(menu);
        return result;
    }

    /**
     * 查询菜单
     * @param menuId
     * @return
     */
    @GetMapping( "/{menuId}")
    public ResponseResult getMenuById(@PathVariable Long menuId){
        ResponseResult result = menuService.getMenuById(menuId);
        return result;
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @PutMapping
    public ResponseResult edit(@RequestBody Menu menu){
        if(menu.getId().equals(menu.getParentId())){
            return ResponseResult.errorResult(500,"修改菜单"+menu.getMenuName()+"失败，上级菜单不能选择自己");
        }
        ResponseResult result=menuService.edit(menu);
        return result;
    }

    /**
     * 删除菜单接口
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult delMenu(@PathVariable Long id){
        ResponseResult result=menuService.delMenu(id);
        return result;
    }

    /**
     * 查询菜单树
     * @return
     */
    @GetMapping("/treeselect")
    public ResponseResult getMenuTree(){
        ResponseResult result = menuService.getMenuTree();
        return result;
    }

    /**
     * 加载对应角色菜单列表
     * @param roleId
     * @return
     */
    @GetMapping("/roleMenuTreeselect/{roleId}")
    public ResponseResult roleMenuTreeSelect(@PathVariable("roleId") Long roleId){
        return null;
    }
}
