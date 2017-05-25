package com.qk.applibrary.util;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.qk.applibrary.bean.AliyuncsOosBean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by acer on 2016-1-6.
 */
public class UploadFileUtil {
    /**
     * 文件转换成byte数组
     * @param filePath
     * @return
     */
    public static byte[] fileToByte(String filePath)
    {
        byte[] buffer = null;
        try
        {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (Exception e)  {
            e.printStackTrace();
        }
        return buffer;
    }
    /**
     * 上传日志文件到阿里云服务器
     * @param oss
     * @param put
     */
    public static void uploadLogFileToAliyuncs(OSS oss, PutObjectRequest put) {
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");

                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
                File file = new File(request.getUploadFilePath());
                file.delete();
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    /**
     * 上传日志文件目录
     * @param mContext
     * @param userName
     * @param files
     */
    public static void uploadLogFileDirectory(Context mContext, String userName, File[] files, AliyuncsOosBean aliyuncsOosBean) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd");
            Date date = new Date();
            String dateStr = dateFormat.format(date);
            dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String dateTimeStr =dateFormat.format(date);
            String endpoint = aliyuncsOosBean.getAliyuncsOosDomain();
            // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
            OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(aliyuncsOosBean.getAliyuncsAccessKeyId(), aliyuncsOosBean.getAliyuncsAccessKeySecret());
            ClientConfiguration conf = new ClientConfiguration();
            conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
            conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
            conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
            conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
            OSS oss = new OSSClient(mContext, endpoint, credentialProvider,conf);
            String objectKey;
            String deviceName  = Build.MODEL; //手机名称
            if(CommonUtil.isEmpty(deviceName)) {
                deviceName = Build.MANUFACTURER;
            }
			//其中的:号 替换为-号 方便用oos客户端下载文件
			dateTimeStr = dateTimeStr.replaceAll(":","-");
            PutObjectRequest put;
            for(File file: files) {
                if(file.getName().contains("error")) {              
                    objectKey = "error/"+dateStr+"/"+userName+"/"+deviceName+"/"+ dateTimeStr+"_"+file.getName();
                } else {
                    objectKey = "api/"+dateStr+"/"+userName+"/"+deviceName+"/"+ dateTimeStr+"_"+file.getName();
                }
                // 构造上传请求
                put = new PutObjectRequest(aliyuncsOosBean.getAliyuncsOosBucket(), objectKey,file.getAbsolutePath());
                uploadLogFileToAliyuncs(oss,put);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
