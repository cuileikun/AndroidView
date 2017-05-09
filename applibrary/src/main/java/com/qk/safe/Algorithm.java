package com.qk.safe;

/**
 * 作者：zhoubenhua
 * 时间：2016-10-26 13:37
 * 功能:算法
 */
public class Algorithm {
    static {
        System.loadLibrary("algorithm");
    }

    /**
     * 获取rsa公钥
     * @return
     */
    public native String getRsaPublicKey();

    /**
     * 获取退房结算单rsa公钥
     * @return
     */
    public native String getCheckOutStatementRsaPublicKey();

    /**
     * 获取房管员卡rsa公钥
     * @return
     */
    public native String getHousekeeperCardRsaPublicKey();



}
