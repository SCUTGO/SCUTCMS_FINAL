package com.scutcms.DAO.access;

import com.scutcms.DAO.entity.Position;
import com.scutcms.DAO.session.SessionFac;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class PositionMapper{
	/**
	 * 向数据库中保存职位信息
	 * @param position
	 */
	public void insert(Position position){
		Session session= SessionFac.INSTANCE.getSession();
		Transaction transaction=session.beginTransaction();
		session.save(position);
		transaction.commit();
		session.close();
	}

	/**
	 * 更新职位信息
	 * @param position
	 */
	public void update(Position position){
		Session session=SessionFac.INSTANCE.getSession();
		Transaction transaction=session.beginTransaction();
		Position oldposition=(Position) session.get(Position.class,position.getPositionName());
		oldposition.setBaseSalary(position.getBaseSalary());
		oldposition.setWageHourly(position.getWageHourly());
		oldposition.setPunishment(position.getPunishment());
		session.update(oldposition);
		transaction.commit();
		session.close();
	}

	/**
	 * 删除职位信息
	 * @param position
	 */
	public void delete(Position position){
		Session session=SessionFac.INSTANCE.getSession();
		Transaction transaction=session.beginTransaction();
		Position oldposition=(Position) session.get(Position.class,position.getPositionName());
		session.delete(oldposition);
		transaction.commit();
		session.close();
	}

	/**
	 * @param PositionName
	 * @return 如果数据库中存在该职位信息，则返回职位的对象；否则返回null
	 */
	public Position getPositionByPositionName(String PositionName){
		Session session=SessionFac.INSTANCE.getSession();
		Position position=(Position) session.get(Position.class,PositionName);
		session.close();
		return position;
	}

	public List<Position> getAllPosition(){
		Session session=SessionFac.INSTANCE.getSession();
		Query query = session.createQuery("from Position");
		List<Position> positionList=(List<Position>)query.list();
		return positionList;
	}
}