package com.scutcms.DAO.access;

import com.scutcms.DAO.entity.Attendance;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class AttendanceMapper {
    /**
     * 向数据库中添加考勤信息
     * @param attendance
     */
    public void insert(Attendance attendance){}

    /**
     * 更新考勤信息
     * @param attendance
     */
    public void update(Attendance attendance){}

    /**
     * 删除考勤信息
     * @param attendance
     */
    public void delete(Attendance attendance){}

    /**
     * 通过员工编号查找该员工的考勤信息
     * @param employId
     * @return 如果数据库中存在该考勤信息，则返回该对象；否则返回null
     */
    public Attendance getAttendanceByEmployeeId(String employId){
        Attendance attendance=new Attendance();
        return attendance;
    }
}
