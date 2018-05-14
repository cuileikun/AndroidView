package com.gitstudy.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mbcloud-cuilk on 2018/5/14.
 */
public class PerUtil {
    /**
     * 检测权限
     *
     * @param activity
     * @param permissions //请求的权限组
     * @param requestCode //请求码
     */
    public static void checkPermission(final Activity activity, final String[] permissions, final int requestCode, permissionInterface permissionInterface) {
        //小于23 就什么都不做
        if (Build.VERSION.SDK_INT < 23) {
            permissionInterface.success();
            return;
        }
        List<String> deniedPermissions = findDeniedPermissions(activity, permissions);
        if (deniedPermissions != null && deniedPermissions.size() > 0) {
            //大于0,表示有权限没申请
            PerUtil.requestContactsPermissions(activity, deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
        } else {
            //拥有权限
            permissionInterface.success();

        }
    }

    /**
     * 请求权限
     */
    private static void requestContactsPermissions(final Activity activity, final String[] permissions, final int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    /**
     * 判断请求权限是否成功
     *
     * @param grantResults
     * @return
     */
    public static boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if (grantResults.length < 1) {
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    //回调接口
    public interface permissionInterface {
        void success();
    }

    /**
     * 找到没有授权的权限
     *
     * @param activity
     * @param permission
     * @return
     */
    private static List<String> findDeniedPermissions(Activity activity, String... permission) {
        //存储没有授权的权限
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (ContextCompat.checkSelfPermission(activity, value) != PackageManager.PERMISSION_GRANTED) {
                //没有权限 就添加
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }
}
