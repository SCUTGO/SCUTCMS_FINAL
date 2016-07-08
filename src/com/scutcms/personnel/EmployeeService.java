package com.scutcms.personnel;

import com.scutcms.DAO.entity.Attendance;
import com.scutcms.DAO.entity.Employee;
import com.scutcms.DAO.entity.Incentive;
import com.scutcms.DAO.access.EmployeeMapper;
import java.util.ArrayList;

/**
 * Created by wujiaming on 7/3/16.
 */
public class EmployeeService {

    private EmployeeMapper employeeMapper;

    public EmployeeService(){
        employeeMapper = new EmployeeMapper();
    }

    /**
     * 检查员工信息的合法性，如果合法，则添加到数据库
     * @param employee
     */
    public boolean insert(Employee employee){

        boolean flag = verifyEmployeeData(employee);
        if (flag){
            employeeMapper.insert(employee);
        }
        return flag;

    }

    /**
     * 更新员工信息
     * @param employee
     */
    public boolean update(Employee employee){
        boolean flag = verifyEmployeeData(employee);
        if(flag){
            employeeMapper.update(employee);
        }
        return flag;
    }

    /**
     * 删除员工信息
     * @param employee
     */
    public boolean delete(Employee employee){
        boolean flag = (employeeMapper.getEmployeeByEmployeeId(employee.getEmployeeId()) != null);
        if(flag){
            employeeMapper.delete(employee);
        }
        return flag;
    }

    /**
     * 查找员工的信息
     * @param employeeID
     * @return 如果该员工存在，则返回该对象；否则，返回null
     */
    public Employee getEmployee(String employeeID){
        Employee employee=new Employee();
        return employee;
    }

    /**
     * 员工签到
     * @param attendance
     */
    public void sign(Attendance attendance){}

    /**
     * 员工请假
     */
    public void askForLeave(){}

    /**
     * 添加奖惩
     * @param incentive
     */
    public void addIncentive(Incentive incentive){}

    /**
     * 计算员工工资
     * @return 员工工资
     */
    public double calculateSalary(){
        double salary=0;
        return salary;
    }

    /**
     * 该类位于服务层，用于获取所有用户列表。
     * @return 返回值为一个包含所有用户的数组。
     */

    public ArrayList<Employee> getAllEmployee(){
        return (ArrayList)employeeMapper.getAllEmployee();
    }

    /**
     * 检查Employee对象中的字段数据是否合法
     * @param employee Employee对象
     * @return 如果所有字段中的数据均合法则返回true，否则返回false
     */
    private boolean verifyEmployeeData(Employee employee){
        boolean flag = true;
        if(employee.getEmployeeId() != null){
            flag &= (employee.getEmployeeId().length() >= 4 && employee.getEmployeeId().length() <= 18);
        }
        if(employee.getPositionName() != null){
            flag &= (employee.getPositionName().length() >= 2 && employee.getPositionName().length() <= 18);
        }
        if(employee.getTelephone() != null){
            flag &= (employee.getTelephone().length() == 11);
        }
        if(employee.getAddress() != null){
            flag &= (employee.getAddress().length() <= 30);
        }
        if(employee.getSfzh() != null){
            flag &= (employee.getSfzh().length() == 18);
        }
        if(employee.getSex() != null){
            flag &= (employee.getSex().length() == 1);
        }
        if(employee.getName() != null){
            flag &= (employee.getName().length() >= 2 );
        }

        flag &= (employee.getAge() >= 18);

        return flag;
    }

}