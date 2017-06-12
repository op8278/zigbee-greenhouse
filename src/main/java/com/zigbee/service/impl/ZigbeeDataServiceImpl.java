package com.zigbee.service.impl;

import com.zigbee.common.ResponseCode;
import com.zigbee.common.ServerResponse;
import com.zigbee.dao.ZigbeeReceiveDataMapper;
import com.zigbee.pojo.ZigbeeReceiveData;
import com.zigbee.service.IZigbeeDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/12.
 */
@Service
public class ZigbeeDataServiceImpl implements IZigbeeDataService{

    @Autowired
    public ZigbeeReceiveDataMapper zigbeeReceiveDataMapper;

    public ServerResponse getLastZigbeeData(){
        ZigbeeReceiveData zigbeeReceiveData = zigbeeReceiveDataMapper.getLastZigbeeData();

        if (zigbeeReceiveData == null){
            return ServerResponse.createBySuccessMessage("获取最新数据错误");
        }

        return ServerResponse.createBySuccess(zigbeeReceiveData);
    }

    public ServerResponse addZigbeeData(ZigbeeReceiveData zigbeeReceiveData){
        //判断参数
        if (StringUtils.isBlank(zigbeeReceiveData.getTemperature())||StringUtils.isBlank(zigbeeReceiveData.getHumidity())){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),"传递zigbee参数错误");
        }
        int rowCount = zigbeeReceiveDataMapper.insert(zigbeeReceiveData);
        if (rowCount ==0){
            return ServerResponse.createByErrorMessage("添加zigbee数据失败");
        }
        return ServerResponse.createBySuccess(zigbeeReceiveData);
    }

}
