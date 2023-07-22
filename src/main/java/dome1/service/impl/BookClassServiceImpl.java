package dome1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dome1.common.R;
import dome1.entity.BookClass;
import dome1.mapper.BookClassMapper;
import dome1.service.BookClassService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookClassServiceImpl extends ServiceImpl<BookClassMapper, BookClass> implements BookClassService {
    @Override
    public BookClass getBookClass(String name) {
        if(StringUtils.isNotEmpty(name)) {
            QueryWrapper<BookClass> wrapper = new QueryWrapper<>();
            wrapper.eq("class_name", name);
            return this.getOne(wrapper);
        }
        return null;
    }

    @Override
    public R<List> getList() {
        List<BookClass> list = this.list();
        return R.success(list);
    }

    @Override
    public R<String> checkName(String bookClass) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("class_name", bookClass);
        BookClass checkClass = this.getOne(wrapper);
        if(checkClass == null) {
            return R.success("未找到重复类型");
        } else {
            return R.error("类型名重复");
        }
    }

    @Override
    public R<String> updateClass(BookClass bookClass) {
        if(bookClass.getClassCode().equals(0L)) {
            bookClass.setClassCode(null);
            this.save(bookClass);
        } else {
            QueryWrapper<BookClass> wrapper = new QueryWrapper();
            wrapper.eq("class_code", bookClass.getClassCode());
            this.update(bookClass, wrapper);
        }
        return R.success("修改成功");
    }
}
