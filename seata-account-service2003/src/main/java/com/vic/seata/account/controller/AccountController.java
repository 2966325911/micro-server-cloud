package com.vic.seata.account.controller;

import com.vic.seata.account.domain.CommonResult;
import com.vic.seata.account.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author：vic
 * @date： 2020/5/31 10:33
 * @desc:
 */
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;


    /**
     * 扣减金额
     * @param userId
     * @param money
     * @return
     */
    @GetMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId,
                                 @RequestParam("money") BigDecimal money){
        accountService.decrease(userId,money);
        return new CommonResult(200,"扣减金额成功");
    }
}
