package com.scutcms.DAO.access;

import com.scutcms.DAO.entity.Salary;
import com.scutcms.DAO.session.SessionFac;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class SalaryMapper {
    /**
     * 向数据库中保存工资信息
     * @param salary
     */
    public void insertSalary(Salary salary){
        Session session= SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        session.save(salary);
        transaction.commit();
        session.close();
    }

    /**
     * 更新工资信息
     * @param salary
     */
    public void updateSalary(Salary salary){
        Session session=SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        Salary oldsalary=(Salary)session.get(Salary.class,salary.getEmployeeID());
        oldsalary.setSalary(salary.getSalary());
        oldsalary.setSalary_DATE(salary.getSalary_DATE());
        session.update(oldsalary);
        transaction.commit();
        session.close();
    }

    /**
     * 删除工资信息
     * @param salary
     */
    public void deleteSalary(Salary salary){
        Session session=SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        Salary oldsalary=(Salary)session.get(Salary.class,salary.getEmployeeID());
        session.delete(oldsalary);
        transaction.commit();
        session.close();
    }

    /**
     * 通过员工工作编号查找工资信息
     * @param employeeId
     * @return 如果数据库中存在该工资信息，则返回工资的对象；否则返回null
     */
    public Salary getSalaryByEmployeeId(String employeeId){
        Session session=SessionFac.INSTANCE.getSession();
        Salary salary=(Salary)session.get(Salary.class,employeeId);
        session.close();
        return salary;
    }
}
