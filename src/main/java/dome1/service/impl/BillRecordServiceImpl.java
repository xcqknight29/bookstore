package dome1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dome1.common.R;
import dome1.entity.Bill;
import dome1.entity.BillRecord;
import dome1.mapper.BillRecordMapper;
import dome1.service.BillRecordService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BillRecordServiceImpl extends ServiceImpl<BillRecordMapper, BillRecord> implements BillRecordService {

    @Autowired
    private BillRecordMapper billRecordMapper;

    @Override
    public R<Page<BillRecord>> getRecordByPage(int page, String input) {
        Page<BillRecord> pageInfo = new Page<>(page, 20);
        QueryWrapper<BillRecord> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(input)) {
            wrapper.like("bill_id", input)
                    .or().like("action_type", input);
        }
        wrapper.orderByDesc("update_time");
        this.page(pageInfo, wrapper);
        return R.success(pageInfo);
    }

    @Override
    public R<String> addBillRecord(Bill bill) {
        BillRecord billRecord = new BillRecord();
        billRecord.setBillId(bill.getId());
        billRecord.setBookId(bill.getBookId());
        billRecord.setBookNum(bill.getNum());
        billRecord.setTotalPrice(bill.getTotalPrice());
        billRecord.setActionType(bill.getBillState());
        this.save(billRecord);
        return R.success("添加记录成功");
    }

    @Override
    public R<IPage> getRecordByMonth(int pageNum, String month) {
        Page page = new Page(pageNum, 20);
        IPage<Map> iPage = billRecordMapper.pageByMonth(page, month);
        return R.success(iPage);
    }

}
