package dome1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dome1.common.R;
import dome1.entity.User;
import dome1.mapper.UserMapper;
import dome1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public R<User> login(HttpServletRequest request, User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", user.getName());
        User checkUser = this.getOne(wrapper);
        if(checkUser == null) {
            //log.info("用户不存在");
            return R.error("用户不存在");
        } else if(!checkUser.getPassword().equals(user.getPassword())) {
            //log.info("密码错误");
            return R.error("密码错误");
        } else {
            //log.info("查询到数据:{}，登录成功", checkUser);
            request.getSession().setAttribute("user", user);
            return R.success(checkUser);
        }
    }

    @Override
    public R<String> userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return R.success("账号已登出");
    }

    @Override
    public R<Page> getUserPage(int page, String name) {
        Page pageInfo = new Page(page, 20);
        QueryWrapper<User> wrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(name)) {
            wrapper.like("name", name)
                    .or().like("user_name", name)
                    .or().like("phone", name)
                    .or().like("email", name);
        }
        wrapper.orderByDesc("update_time");
        this.page(pageInfo, wrapper);
        List<User> list = pageInfo.getRecords();
        //System.out.println(pageInfo.getRecords() + ":" + list);
        return R.success(pageInfo);
    }

    @Override
    public R<String> addUser(User user) {
        user.setPassword("123456");
        user.setState("1");
        this.save(user);
        return R.success("添加成功");
    }

    @Override
    public R<String> updateUser(User user) {
        this.updateById(user);
        return R.success("修改成功");
    }

    @Override
    public R<String> switchState(User user) {
        this.updateById(user);
        return R.success("切换成功");
    }

    @Override
    public R<String> checkName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        User checkUser = this.getOne(wrapper);
        if(checkUser == null) {
            return R.success("未找到重复用户");
        } else {
            return R.error("账号重复");
        }
    }

    @Override
    public R<String> changePassword(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", user.getName());
        User updateUser = this.getOne(wrapper);
        if(updateUser != null) {
            user.setId(updateUser.getId());
            updateById(user);
            return R.success("修改密码成功");
        } else {
            return R.error("账号不存在");
        }
    }

}
