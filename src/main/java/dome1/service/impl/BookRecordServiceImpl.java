package dome1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dome1.common.R;
import dome1.entity.*;
import dome1.mapper.BookRecordMapper;
import dome1.service.BookRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BookRecordServiceImpl extends ServiceImpl<BookRecordMapper, BookRecord> implements BookRecordService {

    @Autowired
    private BookRecordMapper bookRecordMapper;

    @Override
    public R<String> addRecord(boolean increase, String bookId, String bookName, int num, Double price) {
        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookId(bookId);
        bookRecord.setBookName(bookName);
        if(increase) {
            bookRecord.setNum(num);
        } else {
            bookRecord.setNum(-num);
        }
        bookRecord.setPrice(price);
        this.save(bookRecord);
        return R.success("添加更改记录成功");
    }

    /*public R<Page> getBookPage(int page, String name, BookClass bookClass) {
        Page pageInfo = new Page(page, 20);
        QueryWrapper<Book> wrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(name)) {
            wrapper.like("book_name", name)
                    .or().like("price", name);
        }
        if(bookClass != null) {
            wrapper.or().like( "book_class", bookClass.getClassCode());
        }
        wrapper.orderByDesc("update_time");
        this.page(pageInfo, wrapper);
        List<User> list = pageInfo.getRecords();
        //log.info(list + "");
        return R.success(pageInfo);
    }*/

    @Override
    public R<Page> getRecordByPage(int page, String input) {
        Page<BookRecord> pageInfo = new Page<>();
        QueryWrapper<BookRecord> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(input)) {
            wrapper.like("bookId", input)
                    .or().like("book_name", input)
                    .or().like("increase", input)
                    .or().like("price", input);
        }
        wrapper.orderByDesc("create_time");
        this.page(pageInfo, wrapper);
        return R.success(pageInfo);
    }

    /**
     * select book_id, sum(book_num) as book_num, sum(total_price) as total_price
     * from bookRecord
     * where DATE_FORMAT(create_time,'%Y%M') = DATE_FORMAT(DATE_ADD(date,INTERVAL expr unit),format)
     * GROUP BY book_id;
     * order by book_num
     * */

    @Override
    public IPage getRecordByMonth(int pageNum, String month) {
        Page<BookRecord> page = new Page<>(pageNum, 20);
        //QueryWrapper<String> wrapper = new QueryWrapper<>();
        IPage<Map<String, Object>> iPage = bookRecordMapper.pageByMonth(page, month);
        /*wrapper
                .select("book_id, sum(num) as bookNum, sum(price) as totalPrice")
                .apply("DATE_FORMAT(create_time,'%Y%M') = DATE_FORMAT(DATE_ADD({0},INTERVAL 1 DAY),'%Y%M')", month)
                .groupBy("book_id")
                .orderByAsc("bookNum");*/
        //List<Map<String, Object>> list = iPage.getRecords();
        return iPage;
    }

}
