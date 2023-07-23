package dome1.controller;

import dome1.common.R;
import dome1.entity.BookClass;
import dome1.service.BookClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book/class")
public class BookClassController {
    @Autowired
    private BookClassService bookClassService;

    @GetMapping
    public R<List> getList() {
        return bookClassService.getList();
    }

    @GetMapping("check")
    public R<String> checkName(String name) {
        //log.info(name);
        return bookClassService.checkName(name);
    }

    @PostMapping
    public R<String> updateClass(@RequestBody BookClass bookClass) {
        return bookClassService.updateClass(bookClass);
    }
}
