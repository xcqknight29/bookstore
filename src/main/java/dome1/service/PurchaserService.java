package dome1.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import dome1.common.R;
import dome1.entity.Purchaser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaserService extends IService<Purchaser> {

    R<Page<Purchaser>> getPurchaserByPage(int pageNum, String input);

    R<String> addPurchaser(Purchaser purchaser);

    R<String> updatePurchaser(Purchaser purchaser);

    R<List<Purchaser>> getAllPurchaser();

}
