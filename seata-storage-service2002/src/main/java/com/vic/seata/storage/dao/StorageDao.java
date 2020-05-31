package com.vic.seata.storage.dao;

/**
 * @author：vic
 * @date： 2020/5/31 17:13
 * @desc:
 */

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface StorageDao {

    /**
     * 扣减库存
     * @param productId
     * @param count
     */
    void decrease(@Param("productId")Long productId, @Param("count")Integer count);
}
