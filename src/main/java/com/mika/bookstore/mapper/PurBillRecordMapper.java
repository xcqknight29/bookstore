package dome1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import dome1.entity.PurBillRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface PurBillRecordMapper extends BaseMapper<PurBillRecord> {
    @Select("""
            SELECT book_id AS bookId, sum(book_num) AS bookNum, sum(total_price) AS totalPrice
            FROM pur_bill_record
            WHERE DATE_FORMAT(create_time,'%Y%M') = DATE_FORMAT(DATE_ADD(#{month},INTERVAL 1 DAY),'%Y%M') AND order_state = 2
            GROUP BY bookId
            ORDER BY totalPrice
            """)
    IPage<Map> pageByMonth(IPage<PurBillRecord> page, @Param("month") String month);
}
