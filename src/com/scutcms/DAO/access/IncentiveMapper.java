package com.scutcms.DAO.access;

import com.scutcms.DAO.entity.Incentive;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class IncentiveMapper {
    /**
     * 向数据库中保存奖惩信息
     * @param incentive
     */
    public void insert(Incentive incentive){

    }

    /**
     * 更新奖惩信息
     * @param incentive
     */
    public void update(Incentive incentive){

    }

    /**
     * 删除奖惩信息
     * @param incentive
     */
    public void delete(Incentive incentive){

    }

    /**
     * 通过员工编号查找对应的奖惩信息
     * @param employeeId
     * @return 如果数据库中存在该奖惩信息，则返回该对象；否则返回null
     */
    public Incentive getIncentiveByEmployeeId(String employeeId){
        Incentive incentive=new Incentive();
        return incentive;
    }
}
