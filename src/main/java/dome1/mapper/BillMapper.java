package dome1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dome1.entity.Bill;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillMapper extends BaseMapper<Bill> {
}
