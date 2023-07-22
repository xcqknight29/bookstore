package dome1.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import dome1.common.R;
import dome1.entity.Book;
import dome1.entity.BookClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService extends IService<Book> {
    R<Page> getBookPage(int page, String name, BookClass bookClass);

    R<String> checkName(String bookName);

    R<String> addBook(Book book);

    R<String> updateBook(Book book);

    R<String> changeAmount(boolean increase, String id, int num);

    List<Book> getAllBook();
}
