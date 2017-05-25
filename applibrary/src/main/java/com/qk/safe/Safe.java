package com.qk.safe;

/**
 * Created by acer on 2015-12-7.
 */
public class Safe {
    static {
        System.loadLibrary("safe");
    }
    public native String getSendLogFileEmailAccount();//获取发送日志文件邮箱账号
    public native String getReceiverLogFileEmailAccount();//获取接收日志文件邮箱账号

    /**
     * 获取阿里云accessKeyId
     * @return
     */
    public native String getAliyuncsAccessKeyId();
    /**
     * 获取阿里云accessKeySecret
     * @return
     */
    public native String getAliyuncsAccessKeySecret();
    /**
     * 获取阿里云OSS域名
     * @return
     */
    public native String getAliyuncsOosDomain();

    /**
     * 获取阿里云OSS房管员命名空间(日志记录等高级功能的管理实体）
     * @return
     */
    public native String getAliyuncsOosHousekeeperBucket();
    /**
     * 获取阿里云OSS工地锁命名空间
     * @return
     */
    public native String getAliyuncsOosSiteLockBucket();
    /**
     * 获取阿里云OSS青客宝命名空间
     * @return
     */
    public native String getAliyuncsQkPayBucket();


}
