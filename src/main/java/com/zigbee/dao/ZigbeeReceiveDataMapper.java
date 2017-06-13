package com.zigbee.dao;

import com.zigbee.pojo.ZigbeeReceiveData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZigbeeReceiveDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZigbeeReceiveData record);

    int insertSelective(ZigbeeReceiveData record);

    ZigbeeReceiveData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZigbeeReceiveData record);

    int updateByPrimaryKey(ZigbeeReceiveData record);

    ZigbeeReceiveData getLastZigbeeData();

    List<ZigbeeReceiveData> getZigbeeData();

    List<ZigbeeReceiveData> selectByTemperature(@Param("temperature")String temperature);

    List<ZigbeeReceiveData> selectByHumidity(@Param("humidity")String humidity);

    List<ZigbeeReceiveData> selectByTemperatureAndHumidity(@Param("temperature")String temperature,@Param("humidity")String humidity);

    List<ZigbeeReceiveData> selectByTemperatureRange(@Param("from")String from,@Param("to")String to);

    List<ZigbeeReceiveData> selectByHumidityRange(@Param("from")String from,@Param("to")String to);

    List<ZigbeeReceiveData> selectByDateRange(@Param("from")String from,@Param("to")String to);
}