package dome1.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import dome1.common.R;
import dome1.entity.Bill;
import org.springframework.stereotype.Service;

@Service
public interface BillService extends IService<Bill> {

    R<Page<Bill>> getBillPage(int page, String input);

    R<String> changeState(Bill bill);

    R<String> addBill(Bill bill);

}
