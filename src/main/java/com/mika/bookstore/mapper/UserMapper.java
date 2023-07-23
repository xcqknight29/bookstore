package dome1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dome1.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
