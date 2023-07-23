package dome1.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import dome1.common.R;
import dome1.entity.BookRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BookRecordService extends IService<BookRecord> {

    R<String> addRecord(boolean increase, String id, String bookName, int num, Double price);

    R<Page> getRecordByPage(int page, String input);

    IPage getRecordByMonth(int pageNum, String month);

}
