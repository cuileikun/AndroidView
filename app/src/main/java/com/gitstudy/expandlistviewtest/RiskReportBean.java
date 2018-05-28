package com.gitstudy.expandlistviewtest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mbcloud-cuilk on 2018/2/2.
 * 风险报告审批概要
 */
public class RiskReportBean implements Serializable {
    public String RSK_UID = "";//类型：String  必有字段  备注：风险UID
    public String CLT_NAM = "";//类型：String  必有字段  备注：客户名称
    public String ASS_LEV = "";//类型：String  必有字段  备注：资产分类
    public String LEA_AMT = "";//类型：String  必有字段  备注：融资额
    public String RMN_CPS = "";//类型：String  必有字段  备注：资产余额
    public String RMN_MGN = "";//类型：String  必有字段  备注：保证金余额
    public String RSK_EXP = "";//类型：String  必有字段  备注：风险敞口
    public String FST_LON_DTE = "";//类型：String  必有字段  备注：首笔投放日
    public String EXP_DTE = "";//类型：String  必有字段  备注：到期日
    public String CLT_MNG_NAM = "";//类型：String  必有字段  备注：管户人
    public String CLT_DPT = "";//类型：String  必有字段  备注：管户部门
    public String CLT_CRD_LEV = "";//类型：String  必有字段  备注：信用评级
    public String HAS_FLZZ = "";//类型：String  必有字段  备注：有效合同中是否包含直租合同
    public String RSK_ITM = "";//类型：String  必有字段  备注：风险事项内容
    public String ITM_DES = "";//类型：String  必有字段  备注：事件原因详述
    public String ITM_AFF = "";//类型：String  必有字段  备注：评述事件对资产安全的影响
    public String PRO_PLN = "";//类型：String  必有字段  备注：处理预案
    public String ASS_MEO = "";//类型：String  必有字段  备注：资产经理意见
    public String RPT_DTE = "";//类型：String  必有字段  备注：创建日期
    public String RPT_USR = "";//类型：String  必有字段  备注：创建人
    public String CLT_UID = "";//类型：String  必有字段  备注：客户主键
    public String CLT_COD = "";//类型：String  必有字段  备注：客户号
    public String CUR_NO = ""; //类型：String  必有字段  备注：币种

    public List<RiskReportItemBean> CNT_LST = new ArrayList<>();
}
