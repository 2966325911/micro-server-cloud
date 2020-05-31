package com.vic.seata.storage.controller;

import com.vic.seata.storage.domain.CommonResult;
import com.vic.seata.storage.domain.Storage;
import com.vic.seata.storage.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author：vic
 * @date： 2020/5/31 10:33
 * @desc:
 */
@RestController
public class StorageController {
    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @GetMapping("/storage/decrease")
    public CommonResult create(@RequestParam("productId")
                                           Long productId,
                               @RequestParam("count") Integer count){
        storageService.decrease(productId,count);
        return new CommonResult(200,"扣减库存成功");
    }
}
