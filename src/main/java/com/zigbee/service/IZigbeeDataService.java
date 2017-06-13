package com.zigbee.service;

import com.github.pagehelper.PageInfo;
import com.zigbee.common.ServerResponse;
import com.zigbee.pojo.ZigbeeReceiveData;
import com.zigbee.vo.ZigbeeReceiveDataDetailVo;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface IZigbeeDataService {

    public ServerResponse<ZigbeeReceiveData> addZigbeeData(ZigbeeReceiveData zigbeeReceiveData);

    public ServerResponse<ZigbeeReceiveDataDetailVo> getZigbeeDataById(Integer id);

    public ServerResponse<ZigbeeReceiveDataDetailVo> getLastZigbeeData();

    public ServerResponse<PageInfo> getZigbeeData(int pageNum, int pageSize, String orderBy);

    public ServerResponse<PageInfo> getZigbeeDataByTemperatureAndHumidity(String temperature, String humidity, int pageNum, int pageSize, String orderBy);

    public ServerResponse getZigbeeDataByTemperatureRange(String from, String to, int pageNum, int pageSize, String orderBy);

    public ServerResponse getZigbeeDataByHumidityRange(String from, String to, int pageNum, int pageSize, String orderBy);

    public ServerResponse getZigbeeDataByDateRange(String from, String to, int pageNum, int pageSize, String orderBy);


    }
