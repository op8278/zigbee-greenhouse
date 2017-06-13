package com.zigbee.vo;



/**
 * Created by Administrator on 2017/6/13.
 */
public class ZigbeeReceiveDataDetailVo {
    private Integer id;//主键id

    private String temperature;//温度

    private String humidity;//湿度

    private String createTime;//创建时间(注意!!! POJO类型是 date 此处是String)

    private String updateTime;//更新时间(注意!!! POJO类型是 date 此处是String)


    //TODO 待增加更多参数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
