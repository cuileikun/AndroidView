package com.qk.applibrary.bean;

/**
 * Created by acer on 2016-4-21.
 * 阿里云oos实体
 */
public class AliyuncsOosBean {
    /**
     * 阿里云accessKeyId
     */
    private String aliyuncsAccessKeyId;
    /**
     * 阿里云accessKeySecret
     */
    private String aliyuncsAccessKeySecret;
    /**
     * 阿里云OSS域名
     */
    private String aliyuncsOosDomain;
    /**
     * 阿里云oss命名空间
     */
    private String aliyuncsOosBucket;

    public String getAliyuncsAccessKeyId() {
        return aliyuncsAccessKeyId;
    }

    public void setAliyuncsAccessKeyId(String aliyuncsAccessKeyId) {
        this.aliyuncsAccessKeyId = aliyuncsAccessKeyId;
    }

    public String getAliyuncsAccessKeySecret() {
        return aliyuncsAccessKeySecret;
    }

    public void setAliyuncsAccessKeySecret(String aliyuncsAccessKeySecret) {
        this.aliyuncsAccessKeySecret = aliyuncsAccessKeySecret;
    }

    public String getAliyuncsOosDomain() {
        return aliyuncsOosDomain;
    }

    public void setAliyuncsOosDomain(String aliyuncsOosDomain) {
        this.aliyuncsOosDomain = aliyuncsOosDomain;
    }

    public String getAliyuncsOosBucket() {
        return aliyuncsOosBucket;
    }

    public void setAliyuncsOosBucket(String aliyuncsOosBucket) {
        this.aliyuncsOosBucket = aliyuncsOosBucket;
    }
}
