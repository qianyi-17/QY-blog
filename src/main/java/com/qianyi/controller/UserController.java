package com.qianyi.controller;

import com.qianyi.domain.ResponseResult;
import com.qianyi.domain.entity.Role;
import com.qianyi.domain.entity.User;
import com.qianyi.domain.vo.UserInfoAndRoleIdsVo;
import com.qianyi.service.RoleService;
import com.qianyi.service.UserService;
import com.qianyi.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 千亦
 * @create 2022-11-25-15:29
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping("/list")
    public ResponseResult getList(Integer pageNum, Integer pageSize, User user){
        ResponseResult result = userService.getList(pageNum,pageSize,user);
        return result;
    }
    @PostMapping
    public ResponseResult addUser(@RequestBody User user){
        ResponseResult result = userService.addUser(user);
        return result;
    }
    @DeleteMapping("/{userIds}")
    public ResponseResult removeUser(@PathVariable List<Long> userIds){
        if(userIds.contains(SecurityUtils.getUserId())){
            return ResponseResult.errorResult(500,"不能删除当前正在使用的用户");
        }
        userService.removeByIds(userIds);
        return ResponseResult.okResult();
    }
    @GetMapping("/{id}")
    public ResponseResult getUser(@PathVariable Long id){
        List<Role> roles = roleService.selectRoleAll();
        User user = userService.getById(id);
        //查询当前用户具有的角色id
        List<Long> roleIds=roleService.selectRoleIdByUserId(id);
        UserInfoAndRoleIdsVo vo = new UserInfoAndRoleIdsVo(user,roles,roleIds);
        return ResponseResult.okResult(vo);
    }

}
