package com.qk.applibrary.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Window;

import com.qk.applibrary.api.APIAsyncTask;
import com.qk.applibrary.bean.JsonBean;
import com.qk.applibrary.bean.ResponseResult;
import com.qk.applibrary.fragment.BaseFragment;
import com.qk.applibrary.listener.CheckRepeatLoginCallbackInterface;
import com.qk.applibrary.listener.ResponseResultListener;
import com.qk.applibrary.util.CommonUtil;
import com.qk.applibrary.util.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.HashMap;


/**
 * activity基类,所有的activity继承它
 */
public class QkActivity extends Activity {

    private ProgressDialog loadingProgressDialog;

    /**
     * 初始化控件
     */
    public void initViews() {

    }

    public void jumpCommonActivity(Class<? extends BaseFragment> clz) {
        jumpCommonActivity(clz, new JsonBean());
    }

    public void jumpCommonActivity(Class<? extends BaseFragment> clz,
                                   JsonBean info) {
        jumpCommonActivity(clz, info.toString());

    }

    public void jumpCommonActivity(Class<? extends BaseFragment> clz,
                                   String info) {
        Intent intent = new Intent(this,
                clz);
        intent.putExtra("clz", clz.getName());
        if (info != null)
            intent.putExtra("json", info);
        this.startActivity(intent);

    }


    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 打开加载框,用于调用接口弹出的对话框
     *
     * @param title
     * @param message
     * @return
     */
    public ProgressDialog showProgressDialog(String title, String message) {
        try {
            if (isFinishing() == false) {
                if (loadingProgressDialog == null) {
                    loadingProgressDialog = ProgressDialog.show(this, title,
                            message);
                    loadingProgressDialog.setCancelable(true);
                    loadingProgressDialog.setCanceledOnTouchOutside(true);
                } else {
                    loadingProgressDialog.setTitle(title);
                    loadingProgressDialog.setMessage(message);
                    if (loadingProgressDialog.isShowing() == false) {
                        loadingProgressDialog.show();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadingProgressDialog;
    }

    //add by clk on
    /**
     * 打开加载框,用于调用接口弹出的对话框
     *
     * @param title
     * @param message
     * @return
     */
    public ProgressDialog showProgressDialogNew(String title, String message) {
        try {
            if (isFinishing() == false) {
                if (loadingProgressDialog == null) {
                    loadingProgressDialog = ProgressDialog.show(this, title,
                            message);

                    loadingProgressDialog.setCancelable(false);
                    loadingProgressDialog.setCanceledOnTouchOutside(false);
                } else {
                    loadingProgressDialog.setTitle(title);
                    loadingProgressDialog.setMessage(message);
                    if (loadingProgressDialog.isShowing() == false) {
                        loadingProgressDialog.show();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadingProgressDialog;
    }

    //add by clk off

    public ProgressDialog getLoadingProgressDialog() {
        if (loadingProgressDialog != null) {
            return loadingProgressDialog;
        } else {
            return null;
        }
    }

    /**
     * 关闭对话框
     */
    public void dissmissProgressDialog() {
        try {
            if (isFinishing() == false && loadingProgressDialog != null) {
                loadingProgressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加事件
     */
    public void addListeners() {

    }

    /**
     * 检查账号重复
     *
     * @param url          接口地址
     * @param isShowDialog 是否弹出加载框
     * @param info         对话框文字信息
     * @CheckRepeatLoginCallbackInterface 回调接口 回调到所调用的界面
     */
    public void checkRepeatLogin(String url, boolean isShowDialog, String info, final CheckRepeatLoginCallbackInterface checkRepeatLoginCallbackInterface) {
        if (CommonUtil.checkNetwork(this)) {
            /**
             * 检查是否经要弹出加元框
             */
            String userName = SharedPreferencesUtil.getSetting("housekeeper", this, "login_account");
            String deviceId = SharedPreferencesUtil.getSetting("housekeeper", this, "deviceId");
            if (isShowDialog)
                showProgressDialog(null, info);
            APIAsyncTask asyncTask = new APIAsyncTask(this);
            JSONObject json = new JSONObject();
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("userAccount", userName);
            params.put("deviceId", deviceId);
            String apiLogFileDirectory = CommonUtil.getSDCardPath() + "/qk/log";
            String apiLogFileName = "qk_api_log.txt";
            asyncTask.post(apiLogFileDirectory, apiLogFileName, url, params, new ResponseResultListener() {
                @Override
                public void onResult(ResponseResult result) {
                    doSucessCheckRepeatLogin(result, checkRepeatLoginCallbackInterface);
                }
            });
        }
    }

    /**
     * 请求检查账号重复成功
     *
     * @param result
     */
    private void doSucessCheckRepeatLogin(ResponseResult result, CheckRepeatLoginCallbackInterface checkRepeatLoginCallbackInterface) {
        if (result.code == ResponseResult.SUCESS_CODE) {
            /**
             * 账号没有重复登录,返回到所调用的界面
             */
            if (checkRepeatLoginCallbackInterface != null) {
                checkRepeatLoginCallbackInterface.noRepeatLogin(result);
            }
        } else if (result.code == 1) {
            /**
             * 账号重复登录了 要返回到登录界面,再弹出对话框3秒消失
             */
            dissmissProgressDialog();
            /**
             * 发送挤下线广播
             */
            Intent mIntent = new Intent("repeat_login_action");
            mIntent.putExtra("repeat_login_error", result.message);
            sendBroadcast(mIntent);
            finish();
        }
    }

    /**
     * 获取布局id
     *
     * @return
     */
    public int getLayoutId() {
        return 0;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 我们需要判断:该意图是打开一个新的任务,还是将后台的应用给提到前台来.
         * 若是要将应用提到前台来直接将这个Activity结束掉,然后显示出来的Activity就是之前被最小化的Activity.
         因为点击图标的意图会将新启动的Activity置于顶端,而顶端的下面的Activity就是之前被最小化的Activity.
         此时结束掉新启动的Activity,就可以让之前被最小化的Activity 显示出来了.
         */
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            this.finish();
            return;
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        System.gc();
        initViews();
        initData();
        addListeners();
    }


    public void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        showMessageOKCancel(message, "取消", "确定", okListener);

    }


    public void showMessageOKCancel(String message, String leftBtn, String rightBtn, DialogInterface.OnClickListener okListener) {
        android.support.v7.app.AlertDialog dialog = new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(rightBtn, okListener)
                .setNegativeButton(leftBtn, null)
                .create();
        dialog.setCancelable(false);
        dialog.show();
    }


}
