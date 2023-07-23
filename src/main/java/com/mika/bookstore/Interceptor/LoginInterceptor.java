package dome1.Interceptor;

import dome1.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        log.info("拦截到url: {}", request.getServletPath());
        if(user != null) {
            //log.info("用户已登录:{}", user);
            return true;
        } else {
            //log.info("未登录");
            response.sendRedirect("/page/login/login.html");
            return false;
        }
    }
}
