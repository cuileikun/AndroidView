package com.gitstudy.oneandallcheck.program;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mbcloud-cuilk on 2018/2/6.
 * 待办批量审批bean类
 */
public class BatApproveListBean implements Serializable {

    private String TSK_ID = "";//类型：String  必有字段  备注：任务唯一ID
    private String OPN_URL = "";//类型：String  必有字段  备注：任务跳转地址
    private String NOD_NAM = "";//类型：String  必有字段  备注：节点名称
    private String NOD_ID = "";//类型：String  必有字段  备注：节点ID
    private String RCV_DTE = "";//类型：String  必有字段  备注：待办接受时间戳
    private String TIT_NAM = "";//类型：String  必有字段  备注：标题
    private String LEV = "";//类型：String  可有字段  备注：级别，不同级别显示不同颜色 （预警级别：不预警1、一般风险2、观察级3、黄色预警4、红色预警5、风险已暴露6）
    private String LEV_VAL = "";//类型：String  可有字段  备注：级别名称
    private String LFT_COL = "";//类型：String  必有字段  备注：创建人标题
    private String LFT_VAL = "";//类型：String  必有字段  备注：创建人名称
    private String RGT_COL = ""; //类型：String  必有字段  备注：创建日期标题
    private String RGT_VAL = ""; //类型：String  必有字段  备注：创建日期标题
    private boolean isItemSelected;

    public boolean isItemSelected() {
        return isItemSelected;
    }

    public void setItemSelected(boolean itemSelected) {
        isItemSelected = itemSelected;
    }

    private List<BatApproveListItemBean>  BIZ_LST=new ArrayList<>();

    public String getTSK_ID() {
        return TSK_ID;
    }

    public void setTSK_ID(String TSK_ID) {
        this.TSK_ID = TSK_ID;
    }

    public String getOPN_URL() {
        return OPN_URL;
    }

    public void setOPN_URL(String OPN_URL) {
        this.OPN_URL = OPN_URL;
    }

    public String getNOD_NAM() {
        return NOD_NAM;
    }

    public void setNOD_NAM(String NOD_NAM) {
        this.NOD_NAM = NOD_NAM;
    }

    public String getNOD_ID() {
        return NOD_ID;
    }

    public void setNOD_ID(String NOD_ID) {
        this.NOD_ID = NOD_ID;
    }

    public String getRCV_DTE() {
        return RCV_DTE;
    }

    public void setRCV_DTE(String RCV_DTE) {
        this.RCV_DTE = RCV_DTE;
    }

    public String getTIT_NAM() {
        return TIT_NAM;
    }

    public void setTIT_NAM(String TIT_NAM) {
        this.TIT_NAM = TIT_NAM;
    }

    public String getLEV() {
        return LEV;
    }

    public void setLEV(String LEV) {
        this.LEV = LEV;
    }

    public String getLEV_VAL() {
        return LEV_VAL;
    }

    public void setLEV_VAL(String LEV_VAL) {
        this.LEV_VAL = LEV_VAL;
    }

    public String getLFT_COL() {
        return LFT_COL;
    }

    public void setLFT_COL(String LFT_COL) {
        this.LFT_COL = LFT_COL;
    }

    public String getLFT_VAL() {
        return LFT_VAL;
    }

    public void setLFT_VAL(String LFT_VAL) {
        this.LFT_VAL = LFT_VAL;
    }

    public String getRGT_COL() {
        return RGT_COL;
    }

    public void setRGT_COL(String RGT_COL) {
        this.RGT_COL = RGT_COL;
    }

    public String getRGT_VAL() {
        return RGT_VAL;
    }

    public void setRGT_VAL(String RGT_VAL) {
        this.RGT_VAL = RGT_VAL;
    }

    public List<BatApproveListItemBean> getBIZ_LST() {
        return BIZ_LST;
    }

    public void setBIZ_LST(List<BatApproveListItemBean> BIZ_LST) {
        this.BIZ_LST = BIZ_LST;
    }
}
