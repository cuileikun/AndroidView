package com.gitstudy.oneandallcheck.program;

import java.io.Serializable;

/**
 * Created by mbcloud-cuilk on 2018/2/6.
 */
public class BatApproveListItemBean implements Serializable {
    private String COL_NAM = "";//类型：String  必有字段  备注：标题名
    private String COL_VAL = "";//类型：String  必有字段  备注：标题值

    public String getCOL_NAM() {
        return COL_NAM;
    }

    public void setCOL_NAM(String COL_NAM) {
        this.COL_NAM = COL_NAM;
    }

    public String getCOL_VAL() {
        return COL_VAL;
    }

    public void setCOL_VAL(String COL_VAL) {
        this.COL_VAL = COL_VAL;
    }
}
