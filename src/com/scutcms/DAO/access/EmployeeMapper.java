package com.scutcms.DAO.access;

import com.scutcms.DAO.entity.Employee;
import com.scutcms.DAO.session.SessionFac;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class EmployeeMapper {
    /**
     * 向数据库中添加职工信息
     * @param employee
     */
    public void insert(Employee employee){
        Session session= SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
    }

    /**
     * 更新职工信息
     * @param employee
     */
    public void update(Employee employee){
        Session session=SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        Employee oldEmployee=(Employee) session.get(Employee.class,employee.getEmployeeId());
        oldEmployee.setAddress(employee.getAddress());
        oldEmployee.setAge(employee.getAge());
        oldEmployee.setEnter_time(employee.getEnter_time());
        oldEmployee.setName(employee.getName());
        oldEmployee.setPositionName(employee.getPositionName());
        oldEmployee.setTelephone(employee.getTelephone());
        oldEmployee.setSex(employee.getSex());
        oldEmployee.setSfzh(employee.getSfzh());
        session.update(oldEmployee);
        transaction.commit();
        session.close();
    }

    /**
     * 删除职工信息
     * @param employee
     */
    public void delete(Employee employee){
        Session session=SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        Employee oldEmployee=(Employee) session.get(Employee.class,employee.getEmployeeId());
        session.delete(oldEmployee);
        transaction.commit();
        session.close();
    }

    /**
     * 通过员工编号查找指定员工信息
     * @param employeeId
     * @return 如果数据库中存在该职工信息，则返回该对象；否则返回null
     */
    public Employee getEmployeeByEmployeeId(String employeeId){
        Session session=SessionFac.INSTANCE.getSession();
        Employee oldEmployee=(Employee) session.get(Employee.class,employeeId);
        session.close();
        return oldEmployee;
    }

    public List<Employee> getAllEmployee(){
        Session session=SessionFac.INSTANCE.getSession();
        Query query = session.createQuery("from Employee");
        List<Employee> employeeList=(List<Employee>)query.list();
        return employeeList;
    }
}
