package dome1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public ModelAndView test() {
        log.info("jump to asd.html");
        return new ModelAndView("page/ASD.html");
    }

    @GetMapping("getPath")
    public void getPath(HttpServletRequest request) {
        log.info("getContextPath: {}", request.getContextPath());
        log.info("getServletPath: {}", request.getServletPath());
        log.info("getRequestURI: {}", request.getRequestURI());
        log.info("getRealPath: {}", request.getRealPath("/"));
    }
}
