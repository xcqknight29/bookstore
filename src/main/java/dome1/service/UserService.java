package dome1.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import dome1.common.R;
import dome1.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {

    R<User> login(HttpServletRequest request, User user);

    R<String> userLogout(HttpServletRequest request);

    R<Page> getUserPage(int page, String name);

    R<String> addUser(User user);

    R<String> updateUser(User user);

    R<String> switchState(User user);

    R<String> checkName(String name);

    R<String> changePassword(User user);
}
