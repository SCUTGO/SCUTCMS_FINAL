package com.scutcms.DAO.access;

import com.scutcms.DAO.entity.Device;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class DeviceMapper {
    /**
     * 向数据库中添加设备信息
     * @param device
     */
    public void insert(Device device){}

    /**
     * 更新设备信息
     * @param device
     */
    public void update(Device device){}

    /**
     * 删除设备信息
     * @param device
     */
    public void delete(Device device){}

    /**
     * 通过设备ID查找指定设备信息
     * @param DeviceID
     * @return 如果数据库中存在该设备，则返回该对象；否则返回null
     */
    public Device getDeviceByDeviceID(String DeviceID){
        Device device=new Device();
        return device;
    }
}
