package com.scutcms.authentication.Service;

import com.scutcms.DAO.access.UserMapper;
import com.scutcms.DAO.entity.User;
import com.scutcms.util.TokenHelper;

/**
 * Created by anjouker on 16-6-17.
 */
public class TokenService {
    private UserMapper userMapper;
    public TokenService(){
        userMapper = new UserMapper();
    }

    /**
     * 为指定用户创建一个token,并且会将该token持久化到数据库
     * @param username 用户名
     * @return 如果用户名合法则返回token值，不合法则返回null
     */
    public String createTokenforUser(String username){
        String token = TokenHelper.createRandomToken(32);
        User user = userMapper.getUserByUsername(username);
        if(user == null){
            return null;
        }else{
            user.setToken(token);
            userMapper.updateUser(user);
            return token;
        }
    }

    /**
     * 检验指定用户的token是否正确
     * @param username 用户名
     * @param token token值
     * @return 如果参数token与用户匹配则返回true，否则返回false
     */
    public boolean isTokenValidforUser(String username, String token){
        User user = userMapper.getUserByUsername(username);
        if (user == null){
            return false;
        }else{
            return user.getToken().equalsIgnoreCase(token);
        }
    }

    /**
     * 为指定设备创建一个token
     * @param deviceId 设备id
     * @return token值
     */
    public String createTokenforDevice(String deviceId){
        return null;
    }

    /**
     * 检验指定设备的token是否正确
     * @param deviceId 设备id
     * @param token token值
     * @return 如果参数token与设备匹配则返回true，否则返回false
     */
    public boolean isTokenValidforDevice(String deviceId, String token){
        return false;
    }

}
