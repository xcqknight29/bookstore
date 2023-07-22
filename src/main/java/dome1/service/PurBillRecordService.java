package dome1.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import dome1.entity.PurBill;
import dome1.entity.PurBillRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PurBillRecordService extends IService<PurBillRecord> {
    String addRecord(PurBill purBill);
    Page getList(int pageNum, String input);
    IPage getByPage(int pageNum, String month);
}
