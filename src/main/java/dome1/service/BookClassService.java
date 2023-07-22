package dome1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dome1.common.R;
import dome1.entity.BookClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookClassService extends IService<BookClass> {

    BookClass getBookClass(String name);

    R<List> getList();

    R<String> checkName(String bookName);

    R<String> updateClass(BookClass bookClass);
}
