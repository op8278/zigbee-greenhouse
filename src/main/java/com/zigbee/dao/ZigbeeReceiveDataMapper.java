package com.zigbee.dao;

import com.zigbee.pojo.ZigbeeReceiveData;

public interface ZigbeeReceiveDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZigbeeReceiveData record);

    int insertSelective(ZigbeeReceiveData record);

    ZigbeeReceiveData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZigbeeReceiveData record);

    int updateByPrimaryKey(ZigbeeReceiveData record);

    ZigbeeReceiveData getLastZigbeeData();
}