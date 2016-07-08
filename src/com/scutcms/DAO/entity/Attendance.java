package com.scutcms.DAO.entity;

import java.util.Date;

/**
 * Created by anjouker on 16-6-18.
 */
public class Attendance {
    /**
     * 员工工作编号
     */
    private String employeeId;
    /**
     * 打卡时间
     */
    private Date attTime;
    /**
     * 打开设备
     */
    private String deviceID;
    /**
     * 进出方向
     */
    private String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Date getAttTime() {
        return attTime;
    }

    public void setAttTime(Date attTime) {
        this.attTime = attTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
