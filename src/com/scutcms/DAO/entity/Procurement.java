package com.scutcms.DAO.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class Procurement {
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 单价
     */
    private double unit_price;
    /**
     * 数量
     */
    private double amount;
    /**
     * 时间
     */
    private Date procureDate;
    /**
     * 操作员
     */
    private String username;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getProcureDate() {
        return procureDate;
    }

    public void setProcureDate(Date procureDate) {
        this.procureDate = procureDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }


}
