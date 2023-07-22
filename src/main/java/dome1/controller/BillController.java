package dome1.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dome1.common.R;
import dome1.entity.Bill;
import dome1.service.BillRecordService;
import dome1.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillRecordService billRecordService;

    @GetMapping
    public R<Page<Bill>> getBill(int page, String input) {
        return billService.getBillPage(page, input);
    }

    @PutMapping("state")
    public R<String> changeState(@RequestBody Bill bill) {
        R<String> r1 = billService.changeState(bill);
        R<String> r2 = billRecordService.addBillRecord(bill);
        if(r1.getCode().equals(1) && r2.getCode().equals(1)) {
            return R.success("更改订单状态成功");
        }
        return R.error("更改失败：" + r1.getMsg() + r2.getMsg());
    }

    @PostMapping
    public R<String> addBill(@RequestBody Bill bill) {
        R<String> r1 = billService.addBill(bill);
        R<String> r2 = billRecordService.addBillRecord(bill);
        if(r1.getCode().equals(1) && r2.getCode().equals(1)) {
            return R.success("更改订单状态成功");
        }
        return R.error("更改失败：" + r1.getMsg() + r2.getMsg());
    }

}
