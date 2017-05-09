package com.qk.applibrary.api;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.qk.applibrary.bean.ResponseResult;
import com.qk.applibrary.listener.ResponseResultListener;
import com.qk.applibrary.util.LogUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by acer on 2016-1-4.
 * 上传图片任务
 */
public class ImageAsyncTask {
    private static ImageAsyncTask instance;
    ExecutorService pool;
    private Context mContext;
    protected static final int SUCCESS_MESSAGE = 0;
    protected static final int FAILURE_MESSAGE = 1;
    public static ImageAsyncTask getInstance(Context context) {
        if(instance == null) {
            instance = new ImageAsyncTask(context);
        }
        return instance;
    }

    private ImageAsyncTask(Context context) {
        mContext = context;
        pool = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * post请求
     * @param apiLogFileDirectory  api日志文件目录
     * @param apiLogFileName api日志文件名
     * @param url  接口地址
     * @param paramMap  参数map
     * @param listener  回调接口
     */
    public void post(String apiLogFileDirectory,String apiLogFileName,String url,HashMap<String, Object> paramMap, ResponseResultListener listener) {
        pool.submit(new UploadImageTask(apiLogFileDirectory, apiLogFileName, url, paramMap, listener));
    }


    /**
     * 上传图片
     */
    private class UploadImageTask implements Runnable {
        private HashMap<String, Object> mParams;
        private String mUrl;
        private ResponseResultListener mListener;
        private String mApiLogFileDirectory;//api 日志文件目录
        private String mApiLogFileName;//api 日志文件名字
        private Handler handler;
        public UploadImageTask(String apiLogFileDirectory,String apiLogFileName,String url,HashMap<String, Object> params, ResponseResultListener listener) {
            mParams = params;
            mUrl = url;
            mListener = listener;
            this.mApiLogFileName = apiLogFileName;
            this.mApiLogFileDirectory = apiLogFileDirectory;
            if(Looper.myLooper() != null) {
                handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg){
                        String response;
                        switch(msg.what) {
                            case SUCCESS_MESSAGE:
                                response = (String)msg.obj;
                                requestFinished(response, mListener);
                                break;
                            case FAILURE_MESSAGE:
                                response = "当前网络不稳定,请在网络良好的情况下重试";
                                requestFailed(mApiLogFileDirectory, mApiLogFileName, mUrl, response, mListener);
                                break;
                        }
                    }
                };
            }
        }


        protected Message obtainMessage(int responseMessage, Object response) {
            Message msg = null;
            if(handler != null){
                msg = this.handler.obtainMessage(responseMessage, response);
            }else{
                msg = Message.obtain();
                msg.what = responseMessage;
                msg.obj = response;
            }
            return msg;
        }

        @Override
        public void run() {
            HashMap<String, Object> hashMap = new HashMap<>();
            ArrayList<FormFile> formFiles = new ArrayList<>();
            Iterator iterator = mParams.entrySet().iterator();
            int i = 0;
            StringBuffer paramStr = new StringBuffer();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                paramStr.append(i == 0 ? "" : "&");
                paramStr.append(entry.getKey() + "=" + entry.getValue());
                i++;
                if (((String) entry.getKey()).contains("imageFile")) {
                    Uri uri = (Uri) entry.getValue();
                    File file = new File(uri.getPath());
                    formFiles.add(new FormFile(file.getName(), (String) entry.getKey(), "image/jpeg", file.getAbsolutePath()));

                } else if (((String) entry.getKey()).contains("imageList")) {
                    Uri uris[]  = (Uri[]) entry.getValue();
                    for(Uri uri:uris) {
                        File file = new File(uri.getPath());
                        formFiles.add(new FormFile(file.getName(), (String) entry.getKey(), "image/jpeg", file.getAbsolutePath()));
                    }
                } else if (((String) entry.getKey()).contains("picfile")) {
                    Uri uri = (Uri) entry.getValue();
                    File file = new File(uri.getPath());
                    formFiles.add(new FormFile(file.getName(), (String) entry.getKey(), "image/jpeg", file.getAbsolutePath()));

                }
                else {
                    hashMap.put((String) entry.getKey(), entry.getValue());
                }
            }
            LogUtil.log("[---------send-----" + "post" + "----]:" + mUrl+ "?" + paramStr.toString(),
                    mApiLogFileDirectory, mApiLogFileName);
            String result = uploadImage(mApiLogFileDirectory, mApiLogFileName, mUrl, hashMap, mListener,formFiles.toArray(new FormFile[]{}));
            if(result != null) {
                handler.sendMessage(obtainMessage(SUCCESS_MESSAGE, result));
            } else {
                handler.sendMessage(obtainMessage(FAILURE_MESSAGE, "当前网络不稳定,请在网络良好的情况下重试"));
            }
        }

        /**
         * 直接通过HTTP协议提交数据到服务器,实现表单提交功能
         *
         * @param actionUrl 上传路径
         * @param params    请求参数 key为参数名,value为参数值
         * @param files     上传文件
         */
        private String uploadImage(String apiLogFileDirectory,String apiLogFileName,String actionUrl, Map<String, Object> params,ResponseResultListener listener,FormFile[] files) {

            final String BOUNDARY = "a1a2a3"; //数据分隔线
            final String MULTIPART_FORM_DATA = "multipart/form-data";
            final String TWO_HYPHENS = "--";
            final String LINE_FEED = "\r\n";
            StringBuilder stringBuilder = null;
            try {
                URL url = new URL(actionUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);//允许输入
                conn.setDoOutput(true);//允许输出
                conn.setUseCaches(false);//不使用Cache
                conn.setRequestMethod("POST");
                conn.setConnectTimeout(20000);//设置连接超时
                conn.setReadTimeout(30000);//设置请求超时
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Charset", "UTF-8");
                conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA + ";boundary=" + BOUNDARY);
                DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
                //上传的表单参数部分，格式请参考文章
                for (Map.Entry<String, Object> entry : params.entrySet()) {//构建表单字段内容
                    outStream.write((TWO_HYPHENS + BOUNDARY + LINE_FEED).getBytes("UTF-8"));
                    outStream.write(("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINE_FEED).getBytes("UTF-8"));
                    outStream.write(LINE_FEED.getBytes("UTF-8"));
                    try {
                        outStream.write(entry.getValue().toString().getBytes("UTF-8"));
                    } catch (Exception error) {
                        if(error instanceof SocketTimeoutException || error instanceof ConnectTimeoutException || error instanceof ConnectException
                                || error instanceof InterruptedIOException || error instanceof SocketException){
                            handler.sendMessage(obtainMessage(FAILURE_MESSAGE, error.getMessage()));
                            return null;
                        } else {
                            error.printStackTrace();
                        }
                    }
                    outStream.write(LINE_FEED.getBytes("UTF-8"));
                }
                //上传的文件部分，格式请参考文章
                for (FormFile file : files) {
                    StringBuilder split = new StringBuilder();
                    split.append(TWO_HYPHENS);
                    split.append(BOUNDARY);
                    split.append(LINE_FEED);
                    split.append("Content-Disposition: form-data; name=\"" + file.getFormName() + "\"; filename=\"" + file.getFileName() + "\"" + LINE_FEED);
                    split.append("Content-Type: " + file.getContentType() + LINE_FEED);
                    split.append(LINE_FEED);
                    outStream.write(split.toString().getBytes("UTF-8"));
                    byte[] cache = new byte[1024];
                    int length = 0;
                    FileInputStream fileInputStream = null;
                    try {
                        fileInputStream = new FileInputStream(file.getPath());

                        while ((length = fileInputStream.read(cache)) != -1) {
                            outStream.write(cache, 0, length);
                        }
                        fileInputStream.close();
                    } catch (Exception error) {
                        if(error instanceof SocketTimeoutException || error instanceof ConnectTimeoutException || error instanceof ConnectException
                                || error instanceof InterruptedIOException || error instanceof SocketException){
                            handler.sendMessage(obtainMessage(FAILURE_MESSAGE, error.getMessage()));
                            return null;
                        } else {
                            error.printStackTrace();
                        }
                    }
                    outStream.write(LINE_FEED.getBytes("UTF-8"));
                }
                outStream.write((TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + LINE_FEED).getBytes("UTF-8"));//数据结束标志
                outStream.flush();
                outStream.close();
                int responseStatusCode = conn.getResponseCode();
                if (responseStatusCode != 200) {
                    conn.disconnect();
                    return null;
                }
                InputStream stream = conn.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream, "UTF-8");

                int ch;
                stringBuilder = new StringBuilder();
                while ((ch = reader.read()) != -1) {
                    stringBuilder.append((char) ch);
                }
                conn.disconnect();
            } catch (Exception error) {
                if(error instanceof SocketTimeoutException || error instanceof ConnectTimeoutException || error instanceof ConnectException
                        || error instanceof InterruptedIOException || error instanceof SocketException){
                    handler.sendMessage(obtainMessage(FAILURE_MESSAGE, error.getMessage()));
                    return null;
                } else {
                    error.printStackTrace();
                }
            }

            if (stringBuilder == null || TextUtils.isEmpty(stringBuilder.toString())) {
                handler.sendMessage(obtainMessage(FAILURE_MESSAGE, "连接失败"));
                return null;
            }
            String jsonString = stringBuilder.toString();
            LogUtil.log("[----result-----sucess----"+actionUrl+"-----]:"+jsonString,apiLogFileDirectory, apiLogFileName);
            return jsonString;
        }
    }

    /**
     * 连接失败
     */
    private void requestFailed(String apiLogFileDirectory,String apiLogFileName,String url, String content,ResponseResultListener listener) {
        LogUtil.log("[----result-----failed----"+url+"-----]:"+content,apiLogFileDirectory, apiLogFileName);
        ResponseResult result = new ResponseResult();
        result.code = -1;
        result.message = "连接失败";
        listener.onResult(result);
    }

    /**
     * 处理服务器返回数据
     */
    private void requestFinished(String result,ResponseResultListener listener) {

        if (StringUtils.isNotBlank(result)) {

            try {
                JSONObject jsons = new JSONObject(result);
                int code = jsons.getInt("result");
                switch (code) {
                    case 0:
                        ResponseResult data = new ResponseResult();
                        data.code = code;
                        data.data = result;
                        data.message = "";
                        listener.onResult(data);
                        break;
                    default:
                        String msg = jsons.get("error").toString();
                        data = new ResponseResult();
                        data.code = code;
                        data.message = msg;
                        listener.onResult(data);
                        break;
                }
            } catch (JSONException e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }
    }




}
