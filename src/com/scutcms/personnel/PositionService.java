package com.scutcms.personnel;

import com.scutcms.DAO.entity.Position;
import com.scutcms.DAO.access.PositionMapper;
import java.util.ArrayList;

/**
 * Created by wujiaming on 7/3/16.
 */
public class PositionService {

    private PositionMapper positionMapper;

    public PositionService(){
        positionMapper = new PositionMapper();
    }

    /**
     * 检查职位信息的合法性，如果合法，则添加到数据库
     * @param position
     */
    public boolean insertPosition(Position position){

        boolean flag = verifyPositionData(position);
        if (flag){
            positionMapper.insert(position);
        }
        return flag;

    }

    /**
     * 更新职位信息
     * @param position
     */
    public boolean updatePosition(Position position){
        boolean flag = verifyPositionData(position);
        if(flag){
            positionMapper.update(position);
        }
        return flag;
    }

    /**
     * 删除职位信息
     * @param position
     */
    public boolean deletePosition(Position position){
        boolean flag = (positionMapper.getPositionByPositionName(position.getPositionName()) != null);
        if(flag){
            positionMapper.delete(position);
        }
        return flag;
    }

    /**
     * 查找职位的信息
     * @param positionName
     * @return 如果该职位存在，则返回该对象；否则，返回null
     */
    public Position getPositionByPositionNamee(String positionName){
        Position position=new Position();
        return position;
    }


    /**
     * 该类位于服务层，用于获取所有职位列表。
     * @return 返回值为一个包含所有职位的数组。
     */

    public ArrayList<Position> getAllPosition(){
        return (ArrayList)positionMapper.getAllPosition();
    }

    /**
     * 检查Position对象中的字段数据是否合法
     * @param position Position对象
     * @return 如果所有字段中的数据均合法则返回true，否则返回false
     */
    private boolean verifyPositionData(Position position){
        boolean flag = true;
        if(position.getPositionName() != null){
            flag &= (position.getPositionName().length() >= 2 && position.getPositionName().length() <= 18);
        }

        flag &= (position.getPunishment() >= 0);

        flag &= (position.getWageHourly() >= 30);

        flag &= (position.getBaseSalary() >= 3000);

        return flag;
    }

}