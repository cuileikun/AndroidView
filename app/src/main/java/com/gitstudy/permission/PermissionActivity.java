package com.gitstudy.permission;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;
import com.gitstudy.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PermissionActivity extends AppCompatActivity {

    //需要申请的权限
    private String[] mSDPermissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,};
    private String[] mCarmePermissions = new String[]{Manifest.permission.CAMERA};
    public static int PERMISSION_SD_CODE = 1;//读写sd卡权限申请code
    public static int PERMISSION_CARME_CODE = 2;//相机拍照权限申请code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_sd, R.id.btn_came})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_sd:
                PerUtil.checkPermission(PermissionActivity.this, mSDPermissions, PERMISSION_SD_CODE, new PerUtil.permissionInterface() {
                    @Override
                    public void success() {
                        //sd卡权限申请成功之后的操作
                        ToastUtils.showShort(PermissionActivity.this, "sd卡权限申请成功之后的操作");
                    }
                });
                break;
            case R.id.btn_came:
                PerUtil.checkPermission(PermissionActivity.this, mCarmePermissions, PERMISSION_CARME_CODE, new PerUtil.permissionInterface() {
                    @Override
                    public void success() {
                        //拍照权限申请之后的操作
                        ToastUtils.showShort(PermissionActivity.this, "拍照权限申请之后的操作");
                    }
                });
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_SD_CODE) {
            if (PerUtil.verifyPermissions(grantResults)) {
                ToastUtils.showShort(PermissionActivity.this, "sd卡权限申请成功之后的操作");
            } else {
                ToastUtils.showShort(PermissionActivity.this, "读写SD卡权限未开，请去设置页面打开!");
            }
        } else if (requestCode == PERMISSION_CARME_CODE) {
            if (PerUtil.verifyPermissions(grantResults)) {
                ToastUtils.showShort(PermissionActivity.this, "拍照权限申请之后的操作");
//                startCamera();
            } else {
                ToastUtils.showShort(PermissionActivity.this, "拍照视频权限未开，请去设置页面打开!");
            }
        }
    }
}
