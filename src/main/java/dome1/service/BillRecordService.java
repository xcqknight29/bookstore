package dome1.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import dome1.common.R;
import dome1.entity.Bill;
import dome1.entity.BillRecord;
import org.springframework.stereotype.Service;

@Service
public interface BillRecordService extends IService<BillRecord> {

    R<Page<BillRecord>> getRecordByPage(int page, String input);

    R<String> addBillRecord(Bill bill);

    R<IPage> getRecordByMonth(int page, String month);

}
