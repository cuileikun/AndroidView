package com.gitstudy.order;

import java.io.Serializable;

/**
 * Created by mbcloud-cuilk on 2018/6/19.
 */
public class WrHisItmeBean implements Serializable {
    public String WRN_LEV_CHN="";//                //类型：String  必有字段  备注：预警信号类别
    public String WRN_LEV = "";
    public String APV_DTE ="";//                 //类型：String  必有字段  备注：预警分类审批通过日期
    public String CRT_USR ="";//              //类型：String  必有字段  备注：创建人
    public String CRT_DTE ="";//                //类型：String  必有字段  备注：创建日期
}
