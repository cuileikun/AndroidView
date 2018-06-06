package com.gitstudy.listview.three;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mbcloud-cuilk on 2018/6/6.
 */
public class NewProjectChangeApproveBean implements Serializable {
    public String PRJ_NBR = "";//20170029",                //类型：String  必有字段  备注：项目编号
    public String APR_NBR = "";//20170029B02",                //类型：String  必有字段  备注：审批编号
    public String DAL_DTE = "";//2017-01-24",                //类型：String  必有字段  备注：项目受理日期
    public String PRO_MTD = "";//ZJ / WT / SF",                //类型：String  必有字段  备注：该项目的采购方式
    public String S1_MOM = "";//建议与承租人常州市春秋乐园旅游发展有限公司开展融资额度20000万元，期限5年的售后回租业务。",                //类型：String  必有字段  备注：初审意见
    public String S2_MOM = "";//通过--同意初审。",                //类型：String  必有字段  备注：复审意见
    public String HLD_TIM_MEM = "";//招银金融租赁有限公司风险审查委员会于XXXX年XX月XX日召开第XX次会议，委员意见如下：",                //类型：String  必有字段  备注：风审会召开时间
    public String HLD_RST = "";//根据《招银金融租赁有限公司风险审查委员会工作制度》，于XXXX年XX月XX日召开第XX次会议，集体审议项目。本次会议参加人数为XX人，其中再议的票数为XX票，不同意票数为XX票，表决结果本项目已决定通过。",                //类型：String  必有字段  备注：风审会结果
    public String TS_MOM = "";//mock",                //类型：String  必有字段  备注：特殊条款
    public String PF_MOM = "";//mock",                //类型：String  必有字段  备注：批复条件
    public String ZH_MOM = "";//mock"                //类型：String  必有字段  备注：批复租后条件

    public ArrayList<ProjectChangeApproveListBean> approveList = new ArrayList<>();

}
