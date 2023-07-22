package dome1.config;

import dome1.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/page/bill/*.html",
                "/page/enter/*.html",
                "/page/login/*.html",
                "/page/record/*.html",
                "/page/storage/*.html",
                "/page/user/user.html",
                "/page/purchase/*.html",
                "/member/login",
                "/member/logout",
                "/index.html",
                "/**/*.css",
                "/**/*.js",
                "/**/*.ico",
                "/**/*.png",
                "/error");
    }
}
