package dome1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dome1.common.R;
import dome1.entity.PurBill;
import dome1.entity.Purchaser;
import dome1.mapper.PurBillMapper;
import dome1.service.PurBillService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PurBillServiceImpl extends ServiceImpl<PurBillMapper, PurBill> implements PurBillService {

    @Override
    public Page getPurBillByPage(int pageNum, String input) {
        Page<PurBill> page = new Page<>(pageNum, 20);
        QueryWrapper<PurBill> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(input)) {
            wrapper.like("id", input)
                    .or().like("purchaseId", input)
                    .or().like("bookId", input)
                    .or().like("bookNum", input);
            switch (input) {
                case "未下单" -> wrapper.or().like("order_state", 0);
                case "待发货" -> wrapper.or().like("order_state", 1);
                case "已发货" -> wrapper.or().like("order_state", 2);
                case "完成" -> wrapper.or().like("order_state", 3);
            }
        }
        wrapper.orderByDesc("update_time");
        this.page(page, wrapper);
        return page;
    }

    @Override
    public String addPurBill(PurBill bill) {
        bill.setOrderState(0);
        this.save(bill);
        return "添加订单成功";
    }

    @Override
    public String updatePurBill(PurBill bill) {
        this.updateById(bill);
        return "修改订单成功";
    }

    @Override
    public String changeBillState(PurBill bill) {
        bill.setOrderState(bill.getOrderState() + 1);
        this.updateById(bill);
        return "修改订单状态成功";
    }

}
