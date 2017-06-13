package com.zigbee.controller;

import com.github.pagehelper.PageInfo;
import com.zigbee.common.ServerResponse;
import com.zigbee.pojo.ZigbeeReceiveData;
import com.zigbee.service.IZigbeeDataService;
import com.zigbee.service.impl.ZigbeeDataServiceImpl;
import com.zigbee.vo.ZigbeeReceiveDataDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;

/**
 * Created by Administrator on 2017/6/12.
 */

//TODO 权限控制方面
@Controller
@RequestMapping(value = "/api/")
public class ZigbeeDataController {

    @Autowired
    private IZigbeeDataService iZigbeeDataService;


    /**
     * 根据zigbee在数据库里的id 来获取zigbee数据
     *  http://localhost:8080/api/zigbee/4
     * @param id zigbee数据在数据库的id
     * @return zigbee pojo 实体类数据(json)
     */
    @RequestMapping(value = "zigbee/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ZigbeeReceiveDataDetailVo> getZigbeeDataById(@PathVariable("id") Integer id) {
        System.out.println("getZigbeeDataById --- " + id);
        return iZigbeeDataService.getZigbeeDataById(id);
    }

    /**
     * 增加zigbee数据
     *  http://localhost:8080/api/zigbee (POST请求)
     * @param zigbeeReceiveData zigbee数据pojo实体类
     * @return 新增加zigbee数据的信息
     */
    @RequestMapping(value = "zigbee", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<ZigbeeReceiveData> addZigbeeData(ZigbeeReceiveData zigbeeReceiveData) {
        System.out.println("addZigbeeData");
        return iZigbeeDataService.addZigbeeData(zigbeeReceiveData);
    }

    /**
     * 获取最新的zigbee数据
     *  http://localhost:8080/api/zigbee/last
     * @return 单条最新的zigbee pojo 实体类数据(json)
     */
    @RequestMapping(value = "zigbee/last", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ZigbeeReceiveDataDetailVo> getLastZigbeeData(Integer id) {
        System.out.println("getLastZigbeeData --- last");
        return iZigbeeDataService.getLastZigbeeData();
    }


    /**
     * 获取全部的zigbee数据(带分页设置)
     *  http://localhost:8080/api/zigbee/all
     * @param pageNum  页数
     * @param pageSize 每页数量
     * @param orderBy  排序方法
     * @return zigbee分页数据(json)
     */
    @RequestMapping(value = "zigbee/all", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> getZigbeeeData(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                   @RequestParam(value = "orderBy", defaultValue = "desc") String orderBy) {
        System.out.println("getZigbeeeData --- all");
        return iZigbeeDataService.getZigbeeData(pageNum, pageSize, orderBy);
    }

    /**
     * 根据温度或湿度获取zigbee数据
     *  http://localhost:8080/api/zigbee/search?temperature=30&humidity=15
     * @param temperature 温度
     * @param humidity    湿度
     * @param pageNum     页数
     * @param pageSize    每页数量
     * @param orderBy     排序方法
     * @return zigbee pojo 实体类数据列表(json)  zigbee分页数据(json)
     */
    @RequestMapping(value = "zigbee/search", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo> getZigbeeDataByTemperatureAndHumidity(@RequestParam(value = "temperature", required = false) String temperature,
                                                                          @RequestParam(value = "humidity", required = false) String humidity,
                                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                                          @RequestParam(value = "orderBy", defaultValue = "desc") String orderBy) {
        System.out.println("getZigbeeDataByTemperatureAndHumidity --- temperature = " + temperature + " --- humidity = " + humidity);
        return iZigbeeDataService.getZigbeeDataByTemperatureAndHumidity(temperature, humidity, pageNum, pageSize, orderBy);
    }

    /**
     * 根据某个温度范围查找zigbee数据
     *  http://localhost:8080/api/zigbee/search/temperature?from=10&to=30
     * @param from 温度开始范围
     * @param to   温度结束范围
     * @param pageNum     页数
     * @param pageSize    每页数量
     * @param orderBy     排序方法
     * @return zigbee pojo 实体类数据列表(json)  zigbee分页数据(json)
     */
    @RequestMapping(value = "zigbee/search/temperature", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getZigbeeDataByTemperatureRange(@RequestParam(value = "from", required = false) String from,
                                                          @RequestParam(value = "to", required = false) String to,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                          @RequestParam(value = "orderBy", defaultValue = "desc") String orderBy) {
        System.out.println("getZigbeeDataByTemperatureRange --- from = " + from + " --- to = " + to);
        return iZigbeeDataService.getZigbeeDataByTemperatureRange(from, to, pageNum, pageSize, orderBy);
    }

    /**
     * 根据某个湿度范围查找zigbee数据
     *  http://localhost:8080/api/zigbee/search/humidity?from=10&to=30
     * @param from 湿度开始范围
     * @param to   湿度结束范围
     * @param pageNum     页数
     * @param pageSize    每页数量
     * @param orderBy     排序方法
     * @return zigbee pojo 实体类数据列表(json)  zigbee分页数据(json)
     */
    @RequestMapping(value = "zigbee/search/humidity", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getZigbeeDataByHumidityRange(@RequestParam(value = "from", required = false) String from,
                                                          @RequestParam(value = "to", required = false) String to,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                          @RequestParam(value = "orderBy", defaultValue = "desc") String orderBy) {
        System.out.println("getZigbeeDataByHumidityRange --- from = " + from + " --- to = " + to);
        return iZigbeeDataService.getZigbeeDataByHumidityRange(from, to, pageNum, pageSize, orderBy);
    }

    /**
     * 根据某个时间段范围查找zigbee数据
     *  http://localhost:8080/api/zigbee/search/date?from=2017-06-13&to=2017-06-14 21:00:00
     * @param from 时间开始范围 yyyy-MM-dd HH:mm:ss
     * @param to   时间结束范围  2017-06-13 21:00:50
     * @param pageNum     页数
     * @param pageSize    每页数量
     * @param orderBy     排序方法
     * @return zigbee pojo 实体类数据列表(json)  zigbee分页数据(json)
     */
    @RequestMapping(value = "zigbee/search/date", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getZigbeeDataByDateRange(@RequestParam(value = "from", required = false) String from,
                                                          @RequestParam(value = "to", required = false) String to,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                          @RequestParam(value = "orderBy", defaultValue = "desc") String orderBy) {
        System.out.println("getZigbeeDataByDateRange --- from = " + from + " --- to = " + to);
        return iZigbeeDataService.getZigbeeDataByDateRange(from, to, pageNum, pageSize, orderBy);
    }


}
