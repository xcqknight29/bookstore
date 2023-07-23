package dome1.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dome1.common.R;
import dome1.entity.BookRecord;
import dome1.service.BookRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("record/book")
public class BookRecordController {

    @Autowired
    private BookRecordService bookRecordService;

    @GetMapping
    public R<Page> getRecordPage(int page, String input) {
        return bookRecordService.getRecordByPage(page, input);
    }

    @GetMapping("month")
    public R<IPage> getRecordMonth(int pageNum, String date) {
//        log.info("pageNum:{}, date:{}", pageNum, date);
        return R.success(bookRecordService.getRecordByMonth(pageNum, date));
    }

}
