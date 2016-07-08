package test;

import com.scutcms.DAO.access.PositionMapper;
import com.scutcms.DAO.entity.Position;

/**
 * Created by loong on 16-7-6.
 */
public class positiontest {
    public static void main(String[] args) {
        PositionMapper positionMapper = new PositionMapper();
        Position position = new Position();
        position.setPositionName("主厨");
        position.setBaseSalary(5000);
        position.setWageHourly(40);
        position.setPunishment(1.2);
        positionMapper.insert(position);
    }
}
