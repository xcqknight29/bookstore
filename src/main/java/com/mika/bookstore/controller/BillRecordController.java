package dome1.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dome1.common.R;
import dome1.entity.BillRecord;
import dome1.service.BillRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("record/bill")
public class BillRecordController {

    @Autowired
    private BillRecordService billRecordService;

    @GetMapping
    public R<Page<BillRecord>> getRecordByPage(int page, String input) {
        return billRecordService.getRecordByPage(page, input);
    }

    @GetMapping("month")
    public R<IPage> getRecordByMonth(int page, String month) {
        return billRecordService.getRecordByMonth(page, month);
    }

}
