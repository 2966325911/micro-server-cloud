package com.vic.seata.order.service;

import com.vic.seata.order.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author：vic
 * @date： 2020/5/31 10:12
 * @desc:
 */

@FeignClient(name = "seata-account-service")
public interface AccountService {
    /**
     * 减账户得金额
     * @param userId
     * @param money
     * @return
     */
    @GetMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money")BigDecimal money);
}

