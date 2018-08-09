package com.gitstudy.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gitstudy.R;
import com.gitstudy.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondPermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_permission);
        ButterKnife.bind(this);
        //android 6.0权限问题
        grantPermission();
    }

    @OnClick({R.id.btn_sd, R.id.btn_came})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_sd:
                /**
                 * 动态申请权限 第三步
                 */
                //判断相机权限是否打开
                if (PermissionsChecker.checkIsLacksPermission(SecondPermissionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ToastUtils.showShort(SecondPermissionActivity.this, "请打开SD卡读写文件权限");
                    grantPermission();
                    return;
                } else {
                    ToastUtils.showShort(SecondPermissionActivity.this, "SD卡读写权限已经打开，可以进行下一步操作");
                    grantPermission();
                }
                break;
            case R.id.btn_came:
                /**
                 * 动态申请权限 第三步
                 */
                //判断相机权限是否打开
                if (PermissionsChecker.checkIsLacksPermission(SecondPermissionActivity.this, Manifest.permission.CAMERA)) {
                    ToastUtils.showShort(SecondPermissionActivity.this, "请打开相机权限");
                    return;
                } else {
                    ToastUtils.showShort(SecondPermissionActivity.this, "相机拍照及视频权限已经打开，可以进行下一步操作");
                }
                break;
        }
    }

    /**
     * 动态申请权限 第一步
     */
    public void grantPermission() {
        PermissionUtil.requestPermissions(this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA
                });

    }

    /**
     * 动态申请权限 第二步
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            if (permissions[i] == Manifest.permission.CAMERA) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtils.showShort(SecondPermissionActivity.this, "请打开相机权限");
                }
            } else if (permissions[i] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtils.showShort(SecondPermissionActivity.this, "请打开SD卡读写文件权限");
                }
            } else if (permissions[i] == Manifest.permission.READ_EXTERNAL_STORAGE) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtils.showShort(SecondPermissionActivity.this, "请打开SD卡读写文件权限");
                }
            }
        }

    }
}
