package dome1.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dome1.common.R;
import dome1.entity.Purchaser;
import dome1.service.PurchaserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("purchase/purchaser")
public class PurchaserController {

    @Autowired
    private PurchaserService purchaserService;

    @GetMapping("page")
    public R<Page<Purchaser>> getPurchaserByPage(int pageNum, String input) {
        return purchaserService.getPurchaserByPage(pageNum, input);
    }

    @PostMapping("add")
    public R<String> addPurchaser(@RequestBody Purchaser purchaser) {
        return purchaserService.addPurchaser(purchaser);
    }

    @PostMapping("update")
    public R<String> updatePurchaser(@RequestBody Purchaser purchaser) {
        return purchaserService.updatePurchaser(purchaser);
    }

    @GetMapping("getAll")
    public R<List<Purchaser>> getAllPurchaser() {
        return purchaserService.getAllPurchaser();
    }

}
