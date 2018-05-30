package com.gitstudy.oneandallcheck.program;

import java.io.Serializable;

/**
 * Created by mbcloud-cuilk on 2018/2/7.
 * 审批意见初始化bean类
 */
public class ApproveOpnBean implements Serializable {
    private String OPN_ID = "";//类型：String  必有字段  备注：审批意见ID
    private String OPN_NAM = "";//类型：String  必有字段  备注：审批意见名

    public String getOPN_ID() {
        return OPN_ID;
    }

    public void setOPN_ID(String OPN_ID) {
        this.OPN_ID = OPN_ID;
    }

    public String getOPN_NAM() {
        return OPN_NAM;
    }

    public void setOPN_NAM(String OPN_NAM) {
        this.OPN_NAM = OPN_NAM;
    }
}
