package com.scutcms.purchased.Service;

import com.scutcms.DAO.access.PositionMapper;
import com.scutcms.DAO.access.ProcurementMapper;
import com.scutcms.DAO.entity.Position;
import com.scutcms.DAO.entity.Procurement;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by anjouker on 16-6-19.
 */
public class ProcurementService {
    private ProcurementMapper procurementMapper;

    public ProcurementService(){
        procurementMapper = new ProcurementMapper();
    }
    /**
     * 增加一组采购信息
     * @param procurement 采购元祖对象
     * @return 添加成功则返回true，失败则返回false
     */
    public boolean addProcurement(Procurement procurement){
        boolean flag = verifyProcurementData(procurement);
        if(flag){
            procurementMapper.insertprocurement(procurement);
        }
        return flag;
    }

    /**
     * 更新一组采购信息
     * @param procurement 采购元祖对象
     * @return 更新成功则返回true，失败则返回false
     */
    public boolean updateProcurement(Procurement procurement){
        boolean flag = verifyProcurementData(procurement);
        if(flag){
            procurementMapper.updateprocurement(procurement);
        }
        return flag;
    }

    /**
     * 删除一组采购信息
     * @param procurement 采购元祖对象
     * @return 删除成功则返回true，失败则返回false
     */
    public boolean deleteProcurement(Procurement procurement){
        procurementMapper.deleteprocurement(procurement);
        return true;
    }

    /**
     * 返回所有的采购信息
     * @return 一个包含所有采购信息的数组
     */
    public ArrayList<Procurement> getAllProcurement(){
        return (ArrayList)procurementMapper.getAllProcurement();
    }

    /**
     * 对某段时间中的采购信息进行归纳
     * @param startdate 开始日期
     * @param enddate 结束日期
     * @return 一个包含指定采购信息的数组
     */
    public ArrayList<Procurement> analyze(Date startdate, Date enddate){
        return null;
    }

    /**
     * 验证采购信息对象中的字段数据是否合法
     * @param procurement 采购元祖对象
     * @return 如果采购信息对象中所有字段数据均合法则返回true，否则返回false
     */
    private boolean verifyProcurementData(Procurement procurement){
        return true;
    }
}
