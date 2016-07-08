package test;

import com.scutcms.DAO.entity.Position;
import com.scutcms.personnel.PositionService;

import java.util.ArrayList;

/**
 * Created by loong on 16-7-4.
 */
public class PositionTest {
    public static void main(String[] args) {
        PositionService positionService=new PositionService();
        Position position=new Position();
        position.setPositionName("chushi");
        position.setWageHourly(30);
        position.setBaseSalary(4000);
        position.setPunishment(0.5);
        ArrayList<Position>positions=positionService.getAllPosition();
        System.out.println(positionService.insertPosition(position));
        for(Position each:positions){
            System.out.println(each.getPositionName());
        }

    }
}
