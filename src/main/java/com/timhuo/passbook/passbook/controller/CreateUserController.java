package com.timhuo.passbook.passbook.controller;

import com.timhuo.passbook.passbook.log.LogConstants;
import com.timhuo.passbook.passbook.log.LogGenerator;
import com.timhuo.passbook.passbook.service.IUserService;
import com.timhuo.passbook.passbook.vo.Response;
import com.timhuo.passbook.passbook.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 创建用户服务
 * @author: Tim_Huo
 * @created: 2020/10/11 15:13
 */
@Slf4j
@RestController
@RequestMapping("/passbook")
public class CreateUserController {

    //创建用户服务
    private final IUserService userService;

    //HttpServletRequest
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public CreateUserController(IUserService userService, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * 创建用户
     * @param user {@link User}
     * @return {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("createuser")
    Response createUser(@RequestBody User user) throws Exception{
        LogGenerator.genLog(
                httpServletRequest,
                -1L,
                LogConstants.ActionName.CREATE_USER,
                user
        );
        return userService.createUser(user);
    }
}
