package com.zigbee.controller;

import com.zigbee.common.ServerResponse;
import com.zigbee.pojo.ZigbeeReceiveData;
import com.zigbee.service.IZigbeeDataService;
import com.zigbee.service.impl.ZigbeeDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.midi.Soundbank;

/**
 * Created by Administrator on 2017/6/12.
 */
@Controller
@RequestMapping(value = "/api/")
public class ZigbeeDataController {

    @Autowired
    public IZigbeeDataService iZigbeeDataService;


    @RequestMapping(value = "zigbee.do")
    @ResponseBody
    public ServerResponse testDataBase(Integer id){

        System.out.println("接受到的参数是"+id);
        return ServerResponse.createBySuccess(id);
    }


    @RequestMapping(value = "get_zigbee")
    @ResponseBody
    public ServerResponse getLastZigbeeData(Integer id){
        System.out.println("getLastZigbeeData");
        return iZigbeeDataService.getLastZigbeeData();
    }

    @RequestMapping(value = "add_zigbee")
    @ResponseBody
    public ServerResponse addZigbeeData(ZigbeeReceiveData zigbeeReceiveData){
        System.out.println("addZigbeeData");
//        return iZigbeeDataService.addZigbeeData()
        return iZigbeeDataService.addZigbeeData(zigbeeReceiveData);
    }




}
