package dome1.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dome1.common.R;
import dome1.entity.Book;
import dome1.entity.PurBill;
import dome1.service.BookRecordService;
import dome1.service.BookService;
import dome1.service.PurBillRecordService;
import dome1.service.PurBillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("purchase/purBill")
public class PurBillController {

    @Autowired
    private PurBillService purBillService;

    @Autowired
    private PurBillRecordService purBillRecordService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRecordService bookRecordService;

    @GetMapping("page")
    public R<Page> getPurBillByPage(int pageNum, String input) {
        return R.success(purBillService.getPurBillByPage(pageNum, input));
    }

    @PostMapping("add")
    public R<String> addPurBill(@RequestBody PurBill bill) {
        //log.info(String.valueOf(bill));
        String r1 = purBillService.addPurBill(bill);
        String r2 = purBillRecordService.addRecord(bill);
        return R.success("添加成功");
    }

    @PostMapping("update")
    public R<String> updatePurBill(@RequestBody PurBill bill) {
        purBillService.updatePurBill(bill);
        purBillRecordService.addRecord(bill);
        return R.success("修改成功");
    }

    @PostMapping("state")
    public R<String> changeBillState(boolean changeStorage, @RequestBody PurBill bill) {
        //log.info(String.valueOf(changeStorage));
        //log.info("正在更改订单：{}, 更改库存？：{}", bill.toString(), changeStorage);
        String message = purBillService.changeBillState(bill);
        Integer state = bill.getOrderState();
        //log.info(String.valueOf(state.equals(2)));
        if(changeStorage && state.equals(2)) {
            //log.info("修改库存");
            R<String> r1 = bookService.changeAmount(true, bill.getBookId().toString(), bill.getBookNum());
            Book book = bookService.getById(bill.getBookId());
            R<String> r2 = bookRecordService.addRecord(true, bill.getBookId().toString(), book.getBookName(), bill.getBookNum(), bill.getTotalPrice());
        }
        purBillRecordService.addRecord(bill);
        return R.success(message);
    }

}
