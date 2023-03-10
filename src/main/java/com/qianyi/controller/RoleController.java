package com.qianyi.controller;

import com.qianyi.domain.ResponseResult;
import com.qianyi.domain.dto.RoleDto;
import com.qianyi.domain.dto.UserDto;
import com.qianyi.domain.entity.Role;
import com.qianyi.service.RoleService;
import com.qianyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 千亦
 * @create 2022-11-24-10:51
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询角色列表
     * @return
     */
    @GetMapping("/list")
    public ResponseResult listRole(UserDto userDto){
        ResponseResult result=roleService.listRole(userDto);
        return result;
    }

    /**
     *修改菜单
     * @param roleDto
     * @return
     */
    @PutMapping("/changeStatus")
    public ResponseResult editMenu(@RequestBody RoleDto roleDto){
        ResponseResult result=roleService.editMenu(roleDto);
        return result;
    }

    /**
     * 新增角色信息
     * @param role
     * @return
     */
    @PostMapping
    public ResponseResult addRole(@RequestBody Role role){
        ResponseResult result= roleService.addRole(role);
        return result;
    }

    /**
     * 角色信息回显
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getRole(@PathVariable Long id){
        Role role = roleService.getById(id);
        return ResponseResult.okResult(role);
    }
//    @PutMapping("/changeStatus")
//    public ResponseResult edit(@RequestBody Role role){
//        roleService.updateRole(role);
//        return ResponseResult.okResult();
//    }

    @DeleteMapping("/{id}")
    public ResponseResult remove(@PathVariable Long id){
        roleService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/listAllRole")
    public ResponseResult listAllRole(){
        ResponseResult result = roleService.listAllRole();
        return result;
    }



}
