package dome1.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import dome1.common.R;
import dome1.entity.PurBill;
import org.springframework.stereotype.Service;

@Service
public interface PurBillService extends IService<PurBill> {

    Page getPurBillByPage(int pageNum, String input);

    String addPurBill(PurBill bill);

    String updatePurBill(PurBill bill);

    String changeBillState(PurBill bill);

}
