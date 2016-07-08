package com.scutcms.DAO.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class Salary {
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getSalary_DATE() {
        return salary_DATE;
    }

    public void setSalary_DATE(Date salary_DATE) {
        this.salary_DATE = salary_DATE;
    }

    /**
     * 员工工作编号

     */
    private String employeeID;
    /**
     * 工资发放时间
     */
    private Date salary_DATE;
    /**
     * 工资
     */
    private double salary;
}
