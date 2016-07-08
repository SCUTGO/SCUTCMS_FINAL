package com.scutcms.DAO.entity;

/**
 * Created by anjouker on 16-6-18.
 */
public class Position {
    /**
     * 职位名
     */
    private String positionName;
    /**
     * 基本工资
     */
    private int baseSalary;
    /**
     * 每小时工资
     */
    private int  wageHourly;
    /**
     * 惩罚比例
     */
    private double punishment;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public double getPunishment() {
        return punishment;
    }

    public void setPunishment(double punishment) {
        this.punishment = punishment;
    }

    public int getWageHourly() {
        return wageHourly;
    }

    public void setWageHourly(int wageHourly) {
        this.wageHourly = wageHourly;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }
}
