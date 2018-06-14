package com.gitstudy.oneandallcheck.onlyonechoice;

/**
 * Created by mbcloud-cuilk on 2018/6/12.
 */
public class SecTestBean extends SecSelectedBean {
    private String USR_ID;
    private String USR_NAM;


    public String getUSR_ID() {
        return USR_ID;
    }

    public void setUSR_ID(String USR_ID) {
        this.USR_ID = USR_ID;
    }

    public String getUSR_NAM() {
        return USR_NAM;
    }

    public void setUSR_NAM(String USR_NAM) {
        this.USR_NAM = USR_NAM;
    }

    public SecTestBean(String USR_ID,String USR_NAM, boolean isSelected) {
        this.USR_ID = USR_ID;
        this.USR_NAM = USR_NAM;
        setSelected(isSelected);
    }

}
