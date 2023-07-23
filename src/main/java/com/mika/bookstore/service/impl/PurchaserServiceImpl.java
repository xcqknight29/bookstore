package dome1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dome1.common.R;
import dome1.entity.Purchaser;
import dome1.mapper.PurchaserMapper;
import dome1.service.PurchaserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaserServiceImpl extends ServiceImpl<PurchaserMapper, Purchaser> implements PurchaserService {

    /*{
        id: '123',
        traderName: 'abc',
        address: 'shenzhen',
        phone: '13212345678',
        contactPerson: 'leeing',
        email: '123@gmail.com',
        homePage: 'https://www.baidu.com/',
        userId: '1'
}*/
    @Override
    public R<Page<Purchaser>> getPurchaserByPage(int pageNum, String input) {
        Page<Purchaser> page = new Page<>(pageNum, 20);
        QueryWrapper<Purchaser> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(input)) {
            wrapper.like("traderName", input)
                    .or().like("address", input)
                    .or().like("phone", input)
                    .or().like("contactPerson", input)
                    .or().like("email", input)
                    .or().like("homePage", input);
        }
        wrapper.orderByDesc("update_time");
        this.page(page, wrapper);
        return R.success(page);
    }

    @Override
    public R<String> addPurchaser(Purchaser purchaser) {
        this.save(purchaser);
        return R.success("添加供货商成功");
    }

    @Override
    public R<String> updatePurchaser(Purchaser purchaser) {
        this.updateById(purchaser);
        return R.success("修改供货商成功");
    }

    @Override
    public R<List<Purchaser>> getAllPurchaser() {
        return R.success(this.list());
    }

}
