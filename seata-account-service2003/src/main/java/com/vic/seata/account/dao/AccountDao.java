package com.vic.seata.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author：vic
 * @date： 2020/5/24 21:32
 * @desc:
 */
@Mapper
public interface AccountDao {
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money")BigDecimal money);
}
