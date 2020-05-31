package com.vic.seata.order.service;

import com.vic.seata.order.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author：vic
 * @date： 2020/5/31 10:12
 * @desc: 库存服务
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {
    /**
     * 减库存
     * @param productId
     * @param count
     * @return
     */
    @GetMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId,
                          @RequestParam("count") Integer count);

}
