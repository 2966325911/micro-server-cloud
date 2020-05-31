package com.vic.seata.storage.service;

import com.vic.seata.storage.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author：vic
 * @date： 2020/5/31 10:12
 * @desc: 库存服务
 */
public interface StorageService {
    /**
     * 减库存
     * @param productId
     * @param count
     * @return
     */
    void decrease(Long productId,Integer count);

}
