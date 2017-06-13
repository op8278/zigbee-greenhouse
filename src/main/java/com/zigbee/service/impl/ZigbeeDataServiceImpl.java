package com.zigbee.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.zigbee.common.Const;
import com.zigbee.common.ResponseCode;
import com.zigbee.common.ServerResponse;
import com.zigbee.dao.ZigbeeReceiveDataMapper;
import com.zigbee.pojo.ZigbeeReceiveData;
import com.zigbee.service.IZigbeeDataService;
import com.zigbee.util.DateUtil;
import com.zigbee.vo.ZigbeeReceiveDataDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
@Service
public class ZigbeeDataServiceImpl implements IZigbeeDataService {

    @Autowired
    private ZigbeeReceiveDataMapper zigbeeReceiveDataMapper;  //zigbeeReceiveData DAO层 操作入口类

    /**
     * 获取最新的zigbee数据
     *
     * @return 单条最新的zigbee pojo 实体类数据(json)
     */
    public ServerResponse<ZigbeeReceiveDataDetailVo> getLastZigbeeData() {
        ZigbeeReceiveData zigbeeReceiveData = zigbeeReceiveDataMapper.getLastZigbeeData();

        if (zigbeeReceiveData == null) {
            return ServerResponse.createBySuccessMessage("获取最新数据错误");
        }
        ZigbeeReceiveDataDetailVo zigbeeReceiveDataDetailVo = assembleZigbeeReceiveDataDetailVo(zigbeeReceiveData);
        return ServerResponse.createBySuccess(zigbeeReceiveDataDetailVo);
    }

    /**
     * 获取全部的zigbee数据(带分页设置)
     *
     * @param pageNum  页数
     * @param pageSize 每页数量
     * @param orderBy  排序方法
     * @return zigbee分页数据(json)
     */
    public ServerResponse<PageInfo> getZigbeeData(int pageNum, int pageSize, String orderBy) {
        //校验参数
        if (!(Const.ORDER_BY_ASC.equals(orderBy) || Const.ORDER_BY_DESC.equals(orderBy))) {
            return ServerResponse.createByErrorMessage("orderBy参数错误");
        }
        //分页开始
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("id " + orderBy);

        List<ZigbeeReceiveData> zigbeeReceiveDataList = zigbeeReceiveDataMapper.getZigbeeData();

        //遍历转化对象为VO
        List<ZigbeeReceiveDataDetailVo> zigbeeReceiveDataDetailVoList = Lists.newArrayList();
        for (ZigbeeReceiveData zigbeeReceiveDataItem : zigbeeReceiveDataList) {
            ZigbeeReceiveDataDetailVo zigbeeReceiveDataDetailVo = assembleZigbeeReceiveDataDetailVo(zigbeeReceiveDataItem);
            zigbeeReceiveDataDetailVoList.add(zigbeeReceiveDataDetailVo);
        }
        //分页结束
        PageInfo pageResult = new PageInfo(zigbeeReceiveDataList);
        pageResult.setList(zigbeeReceiveDataDetailVoList);

        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 增加zigbee数据
     *
     * @param zigbeeReceiveData zigbee数据pojo实体类
     * @return 新增加zigbee数据的信息
     */
    public ServerResponse<ZigbeeReceiveData> addZigbeeData(ZigbeeReceiveData zigbeeReceiveData) {
        //校验参数
        //TODO 正则判断数据格式
        if (StringUtils.isBlank(zigbeeReceiveData.getTemperature()) || StringUtils.isBlank(zigbeeReceiveData.getHumidity())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "传递zigbee参数错误");
        }
        //Id为数据库自增长,防止意外情况出现
//        zigbeeReceiveData.setId(null);
        int rowCount = zigbeeReceiveDataMapper.insert(zigbeeReceiveData);
        System.out.println(rowCount);
        if (rowCount == 0) {
            return ServerResponse.createByErrorMessage("添加zigbee数据失败");
        }
        return ServerResponse.createBySuccess(zigbeeReceiveData);
    }

    /**
     * 根据zigbee在数据库里的id 来查找zigbee数据
     *
     * @param id zigbee数据在数据库的id
     * @return zigbee pojo 实体类数据(json)
     */
    public ServerResponse<ZigbeeReceiveDataDetailVo> getZigbeeDataById(Integer id) {
        //校验参数
        if (id == null || id <= 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "id参数错误");
        }
        ZigbeeReceiveData zigbeeReceiveData = zigbeeReceiveDataMapper.selectByPrimaryKey(id);
        if (zigbeeReceiveData == null) {
            return ServerResponse.createByErrorMessage("数据不存在");
        }
        ZigbeeReceiveDataDetailVo zigbeeReceiveDataDetailVo = assembleZigbeeReceiveDataDetailVo(zigbeeReceiveData);
        return ServerResponse.createBySuccess(zigbeeReceiveDataDetailVo);
    }

    /**
     * 根据温度或湿度查找zigbee数据
     *
     * @param temperature 温度
     * @param humidity    湿度
     * @param pageNum     页数
     * @param pageSize    每页数量
     * @param orderBy     排序方法
     * @return zigbee pojo 实体类数据列表(json)  zigbee分页数据(json)
     */
    public ServerResponse<PageInfo> getZigbeeDataByTemperatureAndHumidity(String temperature, String humidity, int pageNum, int pageSize, String orderBy) {
        //校验参数
        //TODO 正则判断数据格式
        if (StringUtils.isBlank(temperature) && StringUtils.isBlank(humidity)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "传递温度或湿度参数错误");
        }
        if (!(Const.ORDER_BY_ASC.equals(orderBy) || Const.ORDER_BY_DESC.equals(orderBy))) {
            return ServerResponse.createByErrorMessage("orderBy参数错误");
        }
        //分页开始
        PageHelper.startPage(pageNum, pageSize);
        //设置排序
        PageHelper.orderBy("id " + orderBy);

        List<ZigbeeReceiveData> zigbeeReceiveDataList = zigbeeReceiveDataMapper.selectByTemperatureAndHumidity(temperature, humidity);

        //遍历转化对象为VO
        List<ZigbeeReceiveDataDetailVo> zigbeeReceiveDataDetailVoList = Lists.newArrayList();
        for (ZigbeeReceiveData zigbeeReceiveDataItem : zigbeeReceiveDataList) {
            ZigbeeReceiveDataDetailVo zigbeeReceiveDataDetailVo = assembleZigbeeReceiveDataDetailVo(zigbeeReceiveDataItem);
            zigbeeReceiveDataDetailVoList.add(zigbeeReceiveDataDetailVo);
        }

        //分页结束
        PageInfo pageResult = new PageInfo(zigbeeReceiveDataList);
        pageResult.setList(zigbeeReceiveDataDetailVoList);

        return ServerResponse.createBySuccess(pageResult);
    }

    //TODO 根据范围来查找zigbee数据
    //TODO 优化范围查找流程 参数安排 单参数up/down
    //TODO 同时多个范围查找进行
    //TODO 参数正则检测

    /**
     * 根据某个温度范围查找zigbee数据
     *
     * @param from 温度开始范围
     * @param to   温度结束范围
     * @param pageNum     页数
     * @param pageSize    每页数量
     * @param orderBy     排序方法
     * @return zigbee pojo 实体类数据列表(json)  zigbee分页数据(json)
     */
    public ServerResponse getZigbeeDataByTemperatureRange(String from, String to, int pageNum, int pageSize, String orderBy) {
        //校验参数
        if (StringUtils.isBlank(from) || StringUtils.isBlank(to)) {
            //TODO from必须 to不一定
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "from/to参数格式错误");
        }
        //TODO 正则匹配全数字
        if (from.compareTo(to) > 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数范围错误");
        }
        if (!(Const.ORDER_BY_ASC.equals(orderBy) || Const.ORDER_BY_DESC.equals(orderBy))) {
            return ServerResponse.createByErrorMessage("orderBy参数错误");
        }
        //分页开始
        PageHelper.startPage(pageNum, pageSize);
        //排序
        PageHelper.orderBy("id " + orderBy);

        List<ZigbeeReceiveData> zigbeeReceiveDataList = zigbeeReceiveDataMapper.selectByTemperatureRange(from, to);
        //遍历转化对象为VO
        List<ZigbeeReceiveDataDetailVo> zigbeeReceiveDataDetailVoList = Lists.newArrayList();
        for (ZigbeeReceiveData zigbeeReceiveDataItem : zigbeeReceiveDataList) {
            ZigbeeReceiveDataDetailVo zigbeeReceiveDataDetailVo = assembleZigbeeReceiveDataDetailVo(zigbeeReceiveDataItem);
            zigbeeReceiveDataDetailVoList.add(zigbeeReceiveDataDetailVo);
        }
        //分页结束
        PageInfo pageResult = new PageInfo(zigbeeReceiveDataList);
        pageResult.setList(zigbeeReceiveDataDetailVoList);

        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 根据某个湿度范围查找zigbee数据
     *
     * @param from 湿度开始范围
     * @param to   湿度结束范围
     * @param pageNum     页数
     * @param pageSize    每页数量
     * @param orderBy     排序方法
     * @return zigbee pojo 实体类数据列表(json)  zigbee分页数据(json)
     */
    public ServerResponse getZigbeeDataByHumidityRange(String from, String to, int pageNum, int pageSize, String orderBy) {
        //校验参数
        if (StringUtils.isBlank(from) || StringUtils.isBlank(to)) {
            //TODO from必须 to不一定
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "from/to参数格式错误");
        }
        //TODO 正则匹配全数字
        if (from.compareTo(to) > 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数范围错误");
        }
        if (!(Const.ORDER_BY_ASC.equals(orderBy) || Const.ORDER_BY_DESC.equals(orderBy))) {
            return ServerResponse.createByErrorMessage("orderBy参数错误");
        }
        //分页开始
        PageHelper.startPage(pageNum, pageSize);
        //排序
        PageHelper.orderBy("id " + orderBy);

        List<ZigbeeReceiveData> zigbeeReceiveDataList = zigbeeReceiveDataMapper.selectByHumidityRange(from, to);
        //遍历转化对象为VO
        List<ZigbeeReceiveDataDetailVo> zigbeeReceiveDataDetailVoList = Lists.newArrayList();
        for (ZigbeeReceiveData zigbeeReceiveDataItem : zigbeeReceiveDataList) {
            ZigbeeReceiveDataDetailVo zigbeeReceiveDataDetailVo = assembleZigbeeReceiveDataDetailVo(zigbeeReceiveDataItem);
            zigbeeReceiveDataDetailVoList.add(zigbeeReceiveDataDetailVo);
        }
        //分页结束
        PageInfo pageResult = new PageInfo(zigbeeReceiveDataList);
        pageResult.setList(zigbeeReceiveDataDetailVoList);

        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 根据某个时间段范围查找zigbee数据
     *
     * @param from 时间开始范围 yyyy-MM-dd HH:mm:ss
     * @param to   时间结束范围  2017-06-13 21:00:50
     * @param pageNum     页数
     * @param pageSize    每页数量
     * @param orderBy     排序方法
     * @return zigbee pojo 实体类数据列表(json)  zigbee分页数据(json)
     */
    //TODO 若get参数有空格呢? http://localhost:8080/api/zigbee/search/date?from=2017-06-13&to=2017-06-14 21:00:00
    public ServerResponse getZigbeeDataByDateRange(String from, String to, int pageNum, int pageSize, String orderBy) {
        //校验参数
        if (StringUtils.isBlank(from) || StringUtils.isBlank(to)) {
            //TODO from必须 to不一定
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "from/to参数格式错误");
        }
        //TODO 正则匹配全数字
        if (from.compareTo(to) > 0) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "参数范围错误");
        }
        if (!(Const.ORDER_BY_ASC.equals(orderBy) || Const.ORDER_BY_DESC.equals(orderBy))) {
            return ServerResponse.createByErrorMessage("orderBy参数错误");
        }
        //分页开始
        PageHelper.startPage(pageNum, pageSize);
        //排序
        PageHelper.orderBy("id " + orderBy);

        List<ZigbeeReceiveData> zigbeeReceiveDataList = zigbeeReceiveDataMapper.selectByDateRange(from, to);
        //遍历转化对象为VO
        List<ZigbeeReceiveDataDetailVo> zigbeeReceiveDataDetailVoList = Lists.newArrayList();
        for (ZigbeeReceiveData zigbeeReceiveDataItem : zigbeeReceiveDataList) {
            ZigbeeReceiveDataDetailVo zigbeeReceiveDataDetailVo = assembleZigbeeReceiveDataDetailVo(zigbeeReceiveDataItem);
            zigbeeReceiveDataDetailVoList.add(zigbeeReceiveDataDetailVo);
        }
//        //分页结束
        PageInfo pageResult = new PageInfo(zigbeeReceiveDataList);
        pageResult.setList(zigbeeReceiveDataDetailVoList);

        return ServerResponse.createBySuccess(pageResult);
    }




    /**
     * 将 zigbeeReceiveData 转换为=> zigbeeReceiveDataDetailVo
     *
     * @param zigbeeReceiveData 待转换的zigbeeReceiveData POJO类
     * @return
     */
    private ZigbeeReceiveDataDetailVo assembleZigbeeReceiveDataDetailVo(ZigbeeReceiveData zigbeeReceiveData) {
        ZigbeeReceiveDataDetailVo zigbeeReceiveDataDetailVo = new ZigbeeReceiveDataDetailVo();

        zigbeeReceiveDataDetailVo.setId(zigbeeReceiveData.getId());
        zigbeeReceiveDataDetailVo.setTemperature(zigbeeReceiveData.getTemperature());
        zigbeeReceiveDataDetailVo.setHumidity(zigbeeReceiveData.getHumidity());

        //将POJO 里的date类型 转换为 String类型
        zigbeeReceiveDataDetailVo.setCreateTime(DateUtil.dateToString(zigbeeReceiveData.getCreateTime()));
        zigbeeReceiveDataDetailVo.setUpdateTime(DateUtil.dateToString(zigbeeReceiveData.getUpdateTime()));

        return zigbeeReceiveDataDetailVo;
    }

}
