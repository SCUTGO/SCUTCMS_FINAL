package com.scutcms.authentication.Enum;

/**
 * Created by anjouker on 16-6-17.
 */
public enum TokenResult {
    USER_SCOPE,//代表该token为用户所有
    PUNCHED_CARD_SCOPE,//代表该token为打卡机所有
    WECHAT_SCOPE,//代表该token为微信用户所有
    NO_VALID//代表该token不合法
}
