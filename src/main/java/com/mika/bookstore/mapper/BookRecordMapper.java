package dome1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import dome1.entity.BookRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface BookRecordMapper extends BaseMapper<BookRecord> {

    @Select("""
            SELECT book_id AS bookId, sum(num) AS bookNum
            FROM book_record
            WHERE DATE_FORMAT(create_time,'%Y%M') = DATE_FORMAT(DATE_ADD(#{month},INTERVAL 1 DAY),'%Y%M')
            GROUP BY bookId
            ORDER BY bookNum
            """
    )
    IPage<Map<String, Object>> pageByMonth(IPage<BookRecord> page, @Param("month") String month);

    //SELECT book_id, sum(num) AS bookNum, sum(price) AS totalPrice
}
