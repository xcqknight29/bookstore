package dome1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dome1.entity.PurBill;
import dome1.entity.PurBillRecord;
import dome1.mapper.PurBillRecordMapper;
import dome1.service.PurBillRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PurBillRecordServiceImpl extends ServiceImpl<PurBillRecordMapper, PurBillRecord> implements PurBillRecordService {

    @Autowired
    private PurBillRecordMapper purBillRecordMapper;

    @Override
    public String addRecord(PurBill purBill) {
        PurBillRecord record = new PurBillRecord();
        if(purBill.getId() != null){
            record.setPurBillId(purBill.getId());
        }
        record.setBookId(purBill.getBookId());
        record.setBookNum(purBill.getBookNum());
        record.setTotalPrice(purBill.getTotalPrice());
        record.setOrderState(purBill.getOrderState());
        this.save(record);
        return null;
    }

    @Override
    public Page getList(int pageNum, String input) {
        Page<PurBillRecord> page = new Page<>(pageNum, 20);
        QueryWrapper<PurBillRecord> wrapper = new QueryWrapper<>();
        if(input != null) {
            wrapper.like("pur_bill_id", input)
                    .or().like("book_id", input)
                    .or().like("book_num", input)
                    .or().like("total_price", input)
                    .or().like("order_state", input)
                    .orderByDesc("create_time");
        }
        this.page(page, wrapper);
        return page;
    }

    @Override
    public IPage getByPage(int pageNum, String month) {
        Page<PurBillRecord> page = new Page<>(pageNum, 20);
        IPage<Map> iPage = purBillRecordMapper.pageByMonth(page, month);
        return iPage;
    }

}