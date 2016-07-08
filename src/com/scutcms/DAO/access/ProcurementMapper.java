package com.scutcms.DAO.access;

import com.scutcms.DAO.entity.Procurement;
import com.scutcms.DAO.session.SessionFac;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class ProcurementMapper {
    /**
     * 向数据库中保存采购信息
     * @param procurement
     */
    public void insertprocurement(Procurement procurement){
        Session session= SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        session.save(procurement);
        transaction.commit();
        session.close();
    }

    /**
     * 更新采购信息
     * @param procurement
     */
    public void updateprocurement(Procurement procurement){
        Session session=SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        Procurement oldprocurement=(Procurement)session.get(Procurement.class,procurement.getProductName());
        oldprocurement.setAmount(procurement.getAmount());
        oldprocurement.setProcureDate(procurement.getProcureDate());
        oldprocurement.setUnit_price(procurement.getUnit_price());
        oldprocurement.setUsername(procurement.getUsername());
        session.update(oldprocurement);
        transaction.commit();
        session.close();
    }

    /**
     * 删除采购信息
     * @param procurement
     */
    public void deleteprocurement(Procurement procurement){
        Session session=SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        Procurement oldprocurement=(Procurement)session.get(Procurement.class,procurement.getProductName());
        session.delete(oldprocurement);
        transaction.commit();
        session.close();
    }

    /**
     * 通过产品名称查找采购信息
     * @param productName
     * @return 如果数据库中存在该采购信息，则返回采购的对象；否则返回null
     */
    public Procurement getprocurementByproductName(String productName){
        Session session=SessionFac.INSTANCE.getSession();
        Procurement procurement=(Procurement)session.get(Procurement.class,productName);
        session.close();
        return procurement;
    }

    public List<Procurement> getAllProcurement(){
        Session session=SessionFac.INSTANCE.getSession();
        Query query = session.createQuery("from Procurement");
        List<Procurement> procurementList=(List<Procurement>)query.list();
        return procurementList;
    }
}
