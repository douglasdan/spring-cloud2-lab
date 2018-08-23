package com.douglasdan.example1.user.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.douglasdan.framework.common.model.Model;

/**
 * Created by Dougals.Dan on 2018-08-21.
 */
public class User extends Model {

    private Long userId;

    private String userName;

    @JSONField(serialize = false)
    private String userPwd;

    @JSONField(name = "account")
    private String userAccount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
