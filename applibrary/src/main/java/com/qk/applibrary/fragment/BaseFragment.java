package com.qk.applibrary.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qk.applibrary.api.APIAsyncTask;
import com.qk.applibrary.bean.ResponseResult;
import com.qk.applibrary.listener.BaseFragmentInterface;
import com.qk.applibrary.listener.CheckRepeatLoginCallbackInterface;
import com.qk.applibrary.listener.ResponseResultListener;
import com.qk.applibrary.util.CommonUtil;
import com.qk.applibrary.util.SharedPreferencesUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 碎片基类
 * Created by acer on 2015-10-20.
 */
public class BaseFragment extends Fragment implements BaseFragmentInterface{
    private ProgressDialog loadingProgressDialog;
    protected LayoutInflater mInflater;

    /**
     * 当网络访问请求数据结束后的数据回调
     */
    public interface OnNetRequestOverListener{

        //public boolean isRequestDataSuccess();
        List list = new ArrayList();
        public void OnNetRequestOverListener(List list);
    }
    protected OnNetRequestOverListener onNetRequestOverListener;

    public void setOnNetRequestOverListener(OnNetRequestOverListener onNetRequestOverListener) {
        this.onNetRequestOverListener = onNetRequestOverListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = this.mInflater.inflate(getLayoutId(), container, false);
        initViews(view);
        initData();
        addListeners();
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected int getLayoutId() {
        return 0;
    }

    protected View inflateView(int resId) {
        return this.mInflater.inflate(resId, null);
    }

    public boolean onBackPressed() {
        return false;
    }

    /**
     * 加载框是否弹出
     *
     * @return
     */
    public boolean progressDialogIsShow() {
        boolean flag = false;
        if (loadingProgressDialog != null && loadingProgressDialog.isShowing()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 打开加载框,用于调用接口弹出的对话框
     *
     * @param title
     * @param message
     * @return
     */
    protected ProgressDialog showProgressDialog(String title, String message) {
        try {
            if (getActivity().isFinishing() == false) {
                if (loadingProgressDialog == null) {
                    loadingProgressDialog = ProgressDialog.show(getActivity(), title,
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

    /**
     * 关闭对话框
     */
    protected void dissmissProgressDialog() {
        try {
            if (getActivity().isFinishing() == false && loadingProgressDialog != null) {
                loadingProgressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (CommonUtil.checkNetwork(getActivity())) {
            /**
             * 检查是否经要弹出加元框
             */
            String userName = SharedPreferencesUtil.getSetting("housekeeper", getActivity(), "login_account");
            String deviceId = SharedPreferencesUtil.getSetting("housekeeper", getActivity(), "deviceId");
            if (isShowDialog)
                showProgressDialog(null, info);
            APIAsyncTask asyncTask = new APIAsyncTask(getActivity());
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
    private void doSucessCheckRepeatLogin(ResponseResult result,CheckRepeatLoginCallbackInterface checkRepeatLoginCallbackInterface) {
        if (result.code == ResponseResult.SUCESS_CODE) {
            /**
             * 账号没有重复登录,返回到所调用的界面
             */
            if(checkRepeatLoginCallbackInterface != null) {
                checkRepeatLoginCallbackInterface.noRepeatLogin(result);
            }
        } else if(result.code == 1) {
            /**
             * 账号重复登录了 要返回到登录界面,再弹出对话框3秒消失
             */
            dissmissProgressDialog();
            /**
             * 发送挤下线广播
             */
            Intent mIntent = new Intent("repeat_login_action");
            mIntent.putExtra("repeat_login_error",result.message);
            getActivity().sendBroadcast(mIntent);
            getActivity().finish();
        }
    }


    @Override
    public void initViews(View view) {

    }


    @Override
    public void initData() {

    }


    @Override
    public void addListeners() {

    }

    public void loadData() {

    }
}
