package com.vic.seata.account.service.impl;

import com.vic.seata.account.dao.AccountDao;
import com.vic.seata.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author：vic
 * @date： 2020/5/31 10:14
 * @desc:
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;
    @Override
    public void decrease(Long userId, BigDecimal money) {
//        try {
//            // 超时异常处理
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("---------------扣减金额开始");
        accountDao.decrease(userId,money);
        log.info("---------------扣减金额结束");
    }
}
