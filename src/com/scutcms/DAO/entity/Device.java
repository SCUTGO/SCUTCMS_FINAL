package com.scutcms.DAO.entity;

/**
 * Created by anjouker on 16-6-18.
 */
public class Device {
    /**
     * 设备ID
     */
    private String deviceID;
    /**
     * 设备物理地址
     */
    private String real_address;
    /**
     * 设备状态
     */
    private String status;
    /**
     * 设备凭证
     */
    private String token;

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getReal_address() {
        return real_address;
    }

    public void setReal_address(String real_address) {
        this.real_address = real_address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
