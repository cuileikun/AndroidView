package com.gitstudy.gundongtext;

import java.io.Serializable;

/**
 * Created by mbcloud-cuilk on 2018/9/14.
 * 消息列表内层bean类
 */

public class NewMsgItmeBean implements Serializable ,TipsHolderBean.AnnounceHolderBean{
    private String MSG_SYS;//"OA" String  必有字段  备注：所属系统
    private String MSG_CAT;//"mock"String  必有字段  备注：消息大类
    private String MSG_CLZ;//"mock"String  必有字段  备注：消息小类
    private String MSG_UID;//"ea33c52e-033b-4e54-bd80-71166c075b64", String  必有字段  备注：消息主键,用于更新消息状态
    private String MSG_KEY;//"adc76a13-c277-4b9c-96f0-d2ef1b132a7a", String  必有字段  备注：外部系统消息主键，用于打开消息页面
    private String MSG_TYP;//"1", String  必有字段  备注：纯文本：1 文本（链接）：2 图文：3 图文（链接）：4
    private String MSG_TLE;//"测试环境TCP网关迁移",String  必有字段  备注：消息标题
    private String MSG_URL;//"mock", String  必有字段  备注：消息查看地址
    private String PIC_URL;//"mock",  String  必有字段  备注：缩略图地址
    private String MSG_DSC;//"测试环境TCP网关迁移", String  必有字段  备注：消息概述（补充说明）
    private String CRT_DTE;//"2018-02-12", String  必有字段  备注：日期
    private String CRT_TIM;//"11:30:00",  String  必有字段  备注：时间
    private String RCV_DTE;//"1518406200000", String  必有字段  备注：消息接收时间戳
    private String STS;//"A"  ：String  必有字段  备注：消息状态（A：未读 E：已读）

    public String getMSG_SYS() {
        return MSG_SYS;
    }

    public void setMSG_SYS(String MSG_SYS) {
        this.MSG_SYS = MSG_SYS;
    }

    public String getMSG_CAT() {
        return MSG_CAT;
    }

    public void setMSG_CAT(String MSG_CAT) {
        this.MSG_CAT = MSG_CAT;
    }

    public String getMSG_CLZ() {
        return MSG_CLZ;
    }

    public void setMSG_CLZ(String MSG_CLZ) {
        this.MSG_CLZ = MSG_CLZ;
    }

    public String getMSG_UID() {
        return MSG_UID;
    }

    public void setMSG_UID(String MSG_UID) {
        this.MSG_UID = MSG_UID;
    }

    public String getMSG_KEY() {
        return MSG_KEY;
    }

    public void setMSG_KEY(String MSG_KEY) {
        this.MSG_KEY = MSG_KEY;
    }

    public String getMSG_TYP() {
        return MSG_TYP;
    }

    public void setMSG_TYP(String MSG_TYP) {
        this.MSG_TYP = MSG_TYP;
    }

    public String getMSG_TLE() {
        return MSG_TLE;
    }

    public void setMSG_TLE(String MSG_TLE) {
        this.MSG_TLE = MSG_TLE;
    }

    public String getMSG_URL() {
        return MSG_URL;
    }

    public void setMSG_URL(String MSG_URL) {
        this.MSG_URL = MSG_URL;
    }

    public String getPIC_URL() {
        return PIC_URL;
    }

    public void setPIC_URL(String PIC_URL) {
        this.PIC_URL = PIC_URL;
    }

    public String getMSG_DSC() {
        return MSG_DSC;
    }

    public void setMSG_DSC(String MSG_DSC) {
        this.MSG_DSC = MSG_DSC;
    }

    public String getCRT_DTE() {
        return CRT_DTE;
    }

    public void setCRT_DTE(String CRT_DTE) {
        this.CRT_DTE = CRT_DTE;
    }

    public String getCRT_TIM() {
        return CRT_TIM;
    }

    public void setCRT_TIM(String CRT_TIM) {
        this.CRT_TIM = CRT_TIM;
    }

    public String getRCV_DTE() {
        return RCV_DTE;
    }

    public void setRCV_DTE(String RCV_DTE) {
        this.RCV_DTE = RCV_DTE;
    }

    public String getSTS() {
        return STS;
    }

    public void setSTS(String STS) {
        this.STS = STS;
    }

    @Override
    public String getTipText() {
        return getMSG_TLE();
    }

    @Override
    public String getImgUrl() {
        return getPIC_URL();
    }

}
