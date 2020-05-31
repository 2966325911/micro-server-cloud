package com.vic.seata.account.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author：vic
 * @date： 2020/5/31 10:12
 * @desc:
 */

public interface AccountService {
    /**
     * 减账户得金额
     * @param userId
     * @param money
     * @return
     */

    void decrease(@RequestParam("userId") Long userId, @RequestParam("money")BigDecimal money);
}

