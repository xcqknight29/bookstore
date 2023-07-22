package dome1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dome1.common.R;
import dome1.entity.Bill;
import dome1.mapper.BillMapper;
import dome1.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {

    @Override
    public R<Page<Bill>> getBillPage(int page, String input) {
        Page<Bill> pageInfo = new Page<>(page, 20);
        QueryWrapper<Bill> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(input)) {
            wrapper.like("id", input)
                    .or().like("client_name", input)
                    .or().like("phone", input)
                    .or().like("address", input)
                    .or().like("email", input)
                    .or().like("book_name", input)
                    .or().like("user_id", input);
            switch (input) {
                case "未下单" -> wrapper.or().like("bill_state", 0);
                case "待发货" -> wrapper.or().like("bill_state", 1);
                case "已发货" -> wrapper.or().like("bill_state", 2);
                case "完成" -> wrapper.or().like("bill_state", 3);
            }
        }
        wrapper.orderByDesc("create_time");
        this.page(pageInfo, wrapper);
        /*List<Bill> bill = pageInfo.getRecords();
        bill.forEach(System.out::println);*/
        return R.success(pageInfo);
    }

    @Override
    public R<String> changeState(Bill bill) {
        this.updateById(bill);
        return R.success("修改订单状态成功");
    }

    @Override
    public R<String> addBill(Bill bill) {
        bill.setBillState(0);
        if(this.save(bill)){
            return R.success("新增订单成功");
        } else {
            return R.error("操作数据库失败");
        }
    }

}
