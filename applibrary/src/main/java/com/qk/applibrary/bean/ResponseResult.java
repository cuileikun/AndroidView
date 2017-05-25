package com.qk.applibrary.bean;

/**
 * 服务器返回结果
  */
public class ResponseResult {
	public int code;//200成功 否则失败
	public String message;//提示信息
	public String data;//服务器返回的数据	
	public static int SUCESS_CODE = 0;
	public static int FAILED_CODE = 1;
	public static int FAILED_CODE2 = 2;


	public String pushToken;//推送平台的token

}
