package com.lhf.springboot.controller;

import com.lhf.springboot.domain.StgAtsContactLog;
import com.lhf.springboot.service.StgAtsContactLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: StgAtsContactLogController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/7 12:22
 */
@Api("数据可视化接口Api")
@Controller
public class StgAtsContactLogController {
    private Logger logger = LoggerFactory.getLogger(StgAtsContactLogController.class);

    @Autowired
    private StgAtsContactLogService stgAtsContactLogService;

    @ApiOperation("获取所有数据")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<StgAtsContactLog> findByAll(){
        List<StgAtsContactLog> stgAtsContactLogList = stgAtsContactLogService.findByAll();
        logger.info("stgAtsContactLogList = {}", stgAtsContactLogList);
        return stgAtsContactLogList;
    }
}
