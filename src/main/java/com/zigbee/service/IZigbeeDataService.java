package com.zigbee.service;

import com.zigbee.common.ServerResponse;
import com.zigbee.pojo.ZigbeeReceiveData;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface IZigbeeDataService {

    public ServerResponse getLastZigbeeData();

    public ServerResponse addZigbeeData(ZigbeeReceiveData zigbeeReceiveData);
}
