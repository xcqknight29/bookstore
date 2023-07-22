package dome1.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import dome1.common.R;
import dome1.service.PurBillRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("record/purBill")
public class PurBillRecordController {

    @Autowired
    private PurBillRecordService purBillRecordService;

    /**
     * 2023-04-24 22:27:05
     * 2023-03-31T16:00:00.000Z
     */
    @GetMapping
    public R getList(int pageNum, String input) {
        return R.success(purBillRecordService.getList(pageNum, input));
    }

    @GetMapping("month")
    public R<IPage> getByMonth(int pageNum, String month) {
        return R.success(purBillRecordService.getByPage(pageNum, month));
    }

}
