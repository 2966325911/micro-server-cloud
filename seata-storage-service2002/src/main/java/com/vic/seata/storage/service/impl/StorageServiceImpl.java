package com.vic.seata.storage.service.impl;

import com.vic.seata.storage.dao.StorageDao;
import com.vic.seata.storage.domain.Storage;
import com.vic.seata.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author：vic
 * @date： 2020/5/31 10:14
 * @desc:
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId,count);
    }
}
