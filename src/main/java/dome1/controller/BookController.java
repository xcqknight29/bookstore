package dome1.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dome1.common.R;
import dome1.entity.Book;
import dome1.entity.BookClass;
import dome1.service.BookClassService;
import dome1.service.BookRecordService;
import dome1.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookClassService bookClassService;

    @Autowired
    private BookRecordService bookRecordService;

    @GetMapping("page")
    public R<Page> Page(int page, String name) {
        BookClass bookClass = bookClassService.getBookClass(name);
        return bookService.getBookPage(page, name, bookClass);
    }

    @GetMapping("check")
    public R<String> checkName(String name) {
        //log.info(name);
        return bookService.checkName(name);
    }

    @PostMapping
    public R<String> addBook(@RequestBody Book book) {
        //log.info(book.toString());
        return bookService.addBook(book);
    }

    @PutMapping
    public R<String> updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @PatchMapping("storage")
    public R<String> changeAmount(boolean increase, String id, String bookName, int num, Double price) {
        //log.info("increase={} id={} num={}", increase, id, num);
        R<String> r1 = bookService.changeAmount(increase, id, num);
        R<String> r2 = bookRecordService.addRecord(increase, id, bookName, num, price);
        if(r1.getCode().equals(1) && r2.getCode().equals(1)) {
            return R.success("修改库存成功");
        } else return R.error(r1.getData() + "," + r2.getData());

    }

    @GetMapping("all")
    public R<List> getAllBook() {
        return R.success(bookService.getAllBook());
    }
}
