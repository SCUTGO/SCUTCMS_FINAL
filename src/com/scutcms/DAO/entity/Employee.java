package com.scutcms.DAO.entity;

import java.util.Date;

/**
 * Created by anjouker on 16-6-18.
 */


public class Employee {
    /**
     * 员工工作编号
     */
    private String employeeId;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 员工性别
     */
    private String sex;
    /**
     * 年龄
     */
    private int age;
    /**
     * 住址
     */
    private String address;
    /**
     * 联系方式
     */
    private String telephone;
    /**
     * 入职时间
     */
    private Date enter_time;
    /**
     * 岗位
     */
    private String positionName;
    /**
     * 身份证号
     */
    private String sfzh;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionNname) {
        this.positionName = positionNname;
    }

    public Date getEnter_time() {
        return enter_time;
    }

    public void setEnter_time(Date enter_time) {
        this.enter_time = enter_time;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
