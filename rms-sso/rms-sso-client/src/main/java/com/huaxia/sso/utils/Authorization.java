
package com.huaxia.sso.utils;

import java.io.Serializable;

public class Authorization implements Serializable {

    private static final long serialVersionUID = 2903748234792873l;

    // 用户ID
    private int id;
    // 用户名
    private String username;
    // TGC 会话标识
    private String tgc;

    private String mobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTgc() {
        return tgc;
    }

    public void setTgc(String tgc) {
        this.tgc = tgc;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
