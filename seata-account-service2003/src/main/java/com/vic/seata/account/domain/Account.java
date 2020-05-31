package com.vic.seata.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author：vic
 * @date： 2020/5/24 21:02
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
  private Long id;
    /**
     * 用户id
     */
  private Long userId;
    /**
     * 总额
     */
  private  BigDecimal total;
    /**
     * 已用额度
     */
    private BigDecimal used;
    /**
     * 剩余额度
     */
    private BigDecimal residue;
}
