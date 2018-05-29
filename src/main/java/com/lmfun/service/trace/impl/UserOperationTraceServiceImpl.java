package com.lmfun.service.trace.impl;

import com.lmfun.mapper.trace.UserOperationTraceMapper;
import com.lmfun.pojo.po.trace.UserOperationTracePO;
import com.lmfun.service.trace.UserOperationTraceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOperationTraceServiceImpl implements UserOperationTraceService {

    private final Logger logger = LoggerFactory.getLogger(UserOperationTraceServiceImpl.class);

    @Autowired
    UserOperationTraceMapper userOperationTraceMapper;

    @Override
    public int insertUserOperationTrace(UserOperationTracePO userOperationTracePO) {
        logger.info("insertUserOperationTrace插入用户行为记录，{}", userOperationTracePO);
        return userOperationTraceMapper.insertUserOperationTrace(userOperationTracePO);
    }
}
