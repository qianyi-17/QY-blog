package com.qianyi.controller;

import com.qianyi.domain.ResponseResult;
import com.qianyi.domain.entity.LoginUser;
import com.qianyi.domain.entity.Menu;
import com.qianyi.domain.entity.User;
import com.qianyi.domain.vo.AdminUserInfoVo;
import com.qianyi.domain.vo.RoutersVo;
import com.qianyi.domain.vo.UserInfoVo;
import com.qianyi.enums.AppHttpCodeEnum;
import com.qianyi.exception.SystemException;
import com.qianyi.service.AdminLoginService;
import com.qianyi.service.MenuService;
import com.qianyi.service.RoleService;
import com.qianyi.utils.BeanCopyUtils;
import com.qianyi.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 千亦
 * @create 2022-11-06-16:41
 */
@RestController
//@RequestMapping("/user")
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult adminLogin(@RequestBody User user){ //从请求体中获取用户输入的用户名密码
        if(!StringUtils.hasText(user.getUserName())){
            //提示必须传入用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        ResponseResult result = adminLoginService.adminLogin(user);
        return result;
    }

    @PostMapping("/user/logout")
    public ResponseResult adminLogout(){
        ResponseResult result = adminLoginService.adminLogout();
        return result;
    }

    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //1、获取当前登录的用户
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //两种获取当前登录用户的方法 LoginUser loginUser1 = SecurityUtils.getLoginUser();
        //2、根据用户id，查询权限信息
        Long userId = loginUser.getUser().getId();
        List<String> perms=menuService.selectPermsByUserId(userId);
        //3、根据用户id，查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(userId);
//        List<String> roleKeyList =null;
        //4、封装数据返回
        UserInfoVo user = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        AdminUserInfoVo result = new AdminUserInfoVo(perms,roleKeyList,user);
        return ResponseResult.okResult(result);

    }

    @GetMapping("getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        Authentication authentication = SecurityUtils.getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //查询menu，结果是tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        RoutersVo routersVo = new RoutersVo(menus);
        return ResponseResult.okResult(routersVo);
    }





}
