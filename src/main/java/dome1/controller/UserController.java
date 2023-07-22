package dome1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dome1.common.R;
import dome1.entity.User;
import dome1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("member")
public class UserController {

    /**
     * 自动注入UserService
     */
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user) {
        //log.info("用户{}请求登录", user);
        /*QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.like("name", user.getName());
        User checkUser = userService.getOne(wrapper);
        if(checkUser == null) {
            //log.info("用户不存在");
            return R.error("用户不存在");
        } else if(!checkUser.getPassword().equals(user.getPassword())) {
            //log.info("密码错误");
            return R.error("密码错误");
        } else {
            //log.info("查询到数据:{}，登录成功", checkUser);
            request.getSession().setAttribute("user", user.getId());
            return R.success("登录成功");
        }*/
        return userService.login(request, user);
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        return userService.userLogout(request);
    }

    @GetMapping("logCheck")
    public R<String> logCheck() {
        return null;
    }

    @GetMapping("/check")
    public R<String> checkName(String name) {
        return userService.checkName(name);
    }

    /**
     * 用户分页查询
     * @param page
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> getByPage(int page, String name) {
        //log.info("get{page:" + page + "\tname:" + name + "}");
        /*Page pageInfo = new Page(page, 10);
        QueryWrapper<User> wrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(name)) {
            wrapper.like("name", name);
        }
        wrapper.orderByDesc("update_time");
        userService.page(pageInfo, wrapper);
        return R.success(pageInfo);*/
        return userService.getUserPage(page, name);
    }

    /**
     * 添加用户
     * @param request
     * @param user
     * @return
     */
    @PostMapping
    public R<String> add(HttpServletRequest request, @RequestBody User user) {
        log.info("添加用户" + user);
        /*user.setPassword("123456");
        user.setState("1");
        userService.save(user);*/
        return userService.addUser(user);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody User user) {
        log.info("修改用户" + user);
        return userService.updateUser(user);
    }

    /**
     * 修改用户状态（启用 or 停用）
     * @param user
     * @return
     */
    @PatchMapping
    public R<String> state(@RequestBody User user) {
        log.info("切换用户" + user);
        return userService.switchState(user);
    }

    @PutMapping("password")
    public R<String> changePassword(@RequestBody User user) {
        return userService.changePassword(user);
    }

}
