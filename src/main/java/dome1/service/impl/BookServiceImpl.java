package dome1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dome1.common.R;
import dome1.entity.Book;
import dome1.entity.BookClass;
import dome1.entity.User;
import dome1.mapper.BookMapper;
import dome1.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public R<Page> getBookPage(int page, String name, BookClass bookClass) {
        Page<Book> pageInfo = new Page<>(page, 20);
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(name)) {
            wrapper.like("book_name", name)
                    .or().like("price", name)
                    .or().like("user_id", name);
        }
        if(bookClass != null) {
            wrapper.or().like( "book_class", bookClass.getClassCode());
        }
        wrapper.orderByDesc("update_time");
        this.page(pageInfo, wrapper);
        //List<User> list = pageInfo.getRecords();
        //log.info(list + "");
        return R.success(pageInfo);
    }

    @Override
    public R<String> checkName(String bookName) {
        QueryWrapper<Book> wrapper = new QueryWrapper();
        wrapper.eq("book_name", bookName);
        Book book = this.getOne(wrapper);
        if(book == null) {
            return R.success("未找到重名图书");
        } else {
            return R.error("图书已存在");
        }
    }

    @Override
    public R<String> addBook(Book book) {
        book.setBookAmount(0);
        this.save(book);
        return R.success("添加成功");
    }

    @Override
    public R<String> updateBook(Book book) {
        this.updateById(book);
        return R.success("修改成功");
    }

    @Override
    public R<String> changeAmount(boolean increase, String id, int num) {
        Book book = this.getById(id);
        //log.info(book.toString());
        if(increase) {
            book.setBookAmount(book.getBookAmount() + num);
        } else {
            if(book.getBookAmount() >= num) {
                book.setBookAmount(book.getBookAmount() - num);
            } else {
                return R.error("库存不能小于0");
            }
        }
        this.updateById(book);
        return R.success("修改库存成功");
    }

    @Override
    public List<Book> getAllBook() {
        return this.list();
    }

}
