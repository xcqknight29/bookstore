package dome1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dome1.entity.BookRecord;
import dome1.mapper.BookRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewTest {

    @Autowired
    private BookRecordMapper bookRecordMapper;

    @Test
    public void test1() {
        Page<BookRecord> page = new Page<>(1, 20);
        IPage<Map<String, Object>> iPage = bookRecordMapper.pageByMonth(page, "2023-04-01");
        List<Map<String, Object>> list = iPage.getRecords();
        for(int i = 0; i < list.toArray().length; i++) {
            log.info(String.valueOf(list.get(i)));
        }
    }

}
