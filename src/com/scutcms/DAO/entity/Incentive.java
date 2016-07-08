package com.scutcms.DAO.entity;

import java.util.Date;

/**
 * Created by anjouker on 16-6-18.
 */
public class Incentive {
    /**
     * 员工工作编号
     */
    private String employeeId;
    /**
     * 奖惩时间
     */
    private Date re_pu_time;
    /**
     * 奖惩金额
     */
    private double re_pu;
    /**
     * 描述
     */
    private String description;
    /**
     * 操作员
     */
    private String username;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRe_pu() {
        return re_pu;
    }

    public void setRe_pu(double re_pu) {
        this.re_pu = re_pu;
    }

    public Date getRe_pu_time() {
        return re_pu_time;
    }

    public void setRe_pu_time(Date re_pu_time) {
        this.re_pu_time = re_pu_time;
    }
}
