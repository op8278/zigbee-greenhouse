package com.zigbee.pojo;

import java.util.Date;

public class ZigbeeReceiveData {
    private Integer id;

    private String temperature;

    private String humidity;

    private Date createTime;

    private Date updateTime;

    public ZigbeeReceiveData(Integer id, String temperature, String humidity, Date createTime, Date updateTime) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ZigbeeReceiveData() {
        super();
    }

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
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity == null ? null : humidity.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}