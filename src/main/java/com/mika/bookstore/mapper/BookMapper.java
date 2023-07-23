package dome1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dome1.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
