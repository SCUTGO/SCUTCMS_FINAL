package com.scutcms.DAO.access;

import com.scutcms.DAO.entity.User;
import com.scutcms.DAO.session.SessionFac;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
public class UserMapper {

    /**
     * 向数据库中插入用户信息
     * @param user
     */
    public void insertUser(User user){
        Session session=SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    /**
     * 更改用户的信息
     * @param user
     */
    public void updateUser(User user){
        Session session=SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        User olduser=(User)session.get(User.class,user.getUsername());
        olduser.setReal_name(user.getReal_name());
        olduser.setSalt(user.getSalt());
        olduser.setToken(user.getToken());
        olduser.setTelephone(user.getTelephone());
        olduser.setPassword_md5(user.getPassword_md5());
        session.update(olduser);
        transaction.commit();
        session.close();
    }

    /**
     * 从数据库中删除用户
     * @param user
     */
    public void deleteUser(User user){
        Session session=SessionFac.INSTANCE.getSession();
        Transaction transaction=session.beginTransaction();
        User olduser=(User)session.get(User.class,user.getUsername());
        session.delete(olduser);
        transaction.commit();
        session.close();
    }

    /**
     * 查找指定用户的信息
     * @param username
     * @return 如果数据库中存在该用户，则返回用户的对象；否则返回null
     */
    public User getUserByUsername(String username){
        Session session=SessionFac.INSTANCE.getSession();
        User user=(User)session.get(User.class,username);
        session.close();
        return user;
    }

    public List<User> getAllUser(){
        Session session=SessionFac.INSTANCE.getSession();
        Query query = session.createQuery("from User");
        List<User> userList=(List<User>)query.list();
        return userList;
    }
}
