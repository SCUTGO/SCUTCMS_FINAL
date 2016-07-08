package com.scutcms.authentication.Service;

import com.scutcms.DAO.access.UserMapper;
import com.scutcms.DAO.entity.User;

import com.scutcms.authentication.Enum.LoginResult;
import com.scutcms.util.MD5Helper;
import com.scutcms.util.TokenHelper;


import java.util.ArrayList;

/**
 * Created by anjouker on 16-6-17.
 */
public class UserService {
    private UserMapper userMapper;

    public UserService(){
        userMapper = new UserMapper();
    }

    /**
     * 该类位于服务层，用于验证用户名，密码是否匹配，不产生token
     * @param username 登录用户名
     * @param password 登录密码
     * @return 返回值为一个枚举类型，可能的结果有SUCCESS,USERNAME_NO_VALID,PASSWORD_NO_MATCH,OTHER，
     * 分别对应成功，用户名不存在，密码不正确，其他。
     */
    public LoginResult login(String username, String password){
        User user = userMapper.getUserByUsername(username);
        if(user == null){
            return LoginResult.USERNAME_NO_VALID;
        }
        else{
            if(MD5Helper.checkPassword(password + user.getSalt(), user.getPassword_md5())){
                return LoginResult.SUCCESS;
            }
            else{
                return LoginResult.PASSWORD_NO_MATCH;
            }
        }
    }

    /**
     * 该类位于服务层，用于处理用户登出。用户登出之后讲销毁其token。
     * @param username 登录用户名
     */
    public void logoff(String username){
        User user = userMapper.getUserByUsername(username);
        user.setToken("");
        userMapper.updateUser(user);
    }

    /**
     * 该类位于服务层，用于添加用户。
     * @param user 需要添加的用户对象
     * @return 返回值为true代表添加成功，为false代表添加失败
     */

    public boolean addUser(User user){
        boolean flag = verifyUserData(user);
        System.out.println(flag);
        if (flag){
            user.setToken("");
            user.setSalt(TokenHelper.createRandomToken(32));
            user.setPassword_md5(MD5Helper.getMD5String(user.getPassword() + user.getSalt()));
            System.out.println("service.add.user");
            userMapper.insertUser(user);
        }
        return flag;
    }

    /**
     * 该类位于服务层，用于删除用户。
     * @param user 需要删除的用户对象
     * @return 返回值为true代表删除成功，为false代表删除失败，删除失败的原因为该用户不存在
     */

    public boolean deleteUser(User user){
        boolean flag = (userMapper.getUserByUsername(user.getUsername()) != null);
        if(flag){
            userMapper.deleteUser(user);
        }
        return flag;
    }

    /**
     * 该类位于服务层，用于更新用户。
     * @param user 需要更新的用户对象
     * @return 返回值为true代表更新成功，为false代表更新失败
     */

    public boolean updateUser(User user){
        User newuser = userMapper.getUserByUsername(user.getUsername());
        System.out.println("old password:" + newuser.getPassword());
        if(!user.getPassword().equalsIgnoreCase("********")){
            newuser.setPassword(user.getPassword());
            newuser.setPassword_md5(MD5Helper.getMD5String(newuser.getPassword() + newuser.getSalt()));
        }
        System.out.println("new password:" + newuser.getPassword());
        newuser.setTelephone(user.getTelephone());
        newuser.setReal_name(user.getReal_name());

        boolean flag = verifyUserData(newuser);
        if(flag){
            userMapper.updateUser(newuser);
        }
        return flag;
    }

    /**
     * 该类位于服务层，用于获取所有用户列表。
     * @return 返回值为一个包含所有用户的数组。
     */

    public ArrayList<User> getAllUser(){
        return (ArrayList)userMapper.getAllUser();
    }

    /**
     * 检查User对象中的字段数据是否合法
     * @param user User对象
     * @return 如果所有字段中的数据均合法则返回true，否则返回false
     */
    private boolean verifyUserData(User user){
        boolean flag = true;
        if(user.getUsername() != null){
            flag &= (user.getUsername().length() >= 4 && user.getUsername().length() <= 18);
        }
        if(user.getPassword() != null){
            flag &= (user.getPassword().length() >= 8 && user.getPassword().length() <= 18);
        }
        if(user.getReal_name() != null){
            flag &= (user.getReal_name().length() <= 16);
        }
        if(user.getTelephone() != null){
            flag &= (user.getTelephone().length() == 11);
        }
        return flag;
    }
}