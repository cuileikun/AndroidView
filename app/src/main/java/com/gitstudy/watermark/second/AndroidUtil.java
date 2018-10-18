package com.gitstudy.watermark.second;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gitstudy.LeaseApplication;
import com.gitstudy.SPUtil;
import com.gitstudy.lockscreen.util.constant.Constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wch on 2017/9/25.
 */

public class AndroidUtil {

    /**
     * 获取一个月前的日期
     *
     * @return
     */
    public static String getTime3() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String monthAgo = simpleDateFormat.format(new Date().getTime());
        return monthAgo;
    }

    /**
     * 获取一个月前的日期
     *
     * @param date 传入的日期
     * @return
     */
    public static String getMonthAgo(Date date, int m) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, m);
        String monthAgo = simpleDateFormat.format(calendar.getTime());
        return monthAgo;
    }

    /**
     * 获取一个月前的日期
     *
     * @param date 传入的日期
     * @return
     */
    public static String getDayAgo(Date date, int m) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, m);
        String monthAgo = simpleDateFormat.format(calendar.getTime());
        return monthAgo;
    }

    /**
     * 获取一个月前的日期
     *
     * @param date 传入的日期
     * @return
     */
    public static String getWeekAgo(Date date, int m) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, m);
        String monthAgo = simpleDateFormat.format(calendar.getTime());
        return monthAgo;
    }

    public static String getMoneyTag() {
        String html = "<body><font face='Arial'>&yen;</font>";
        return Html.fromHtml(html).toString();
    }

    /**
     * 根据类型获取币种符号
     *
     * @param type 10 人民币， 32 美元
     * @return 币种符号
     */
    public static String getMoneyTagByType(String type) {
        if ("10".equals(type)) {
            // 人民币符号
            String html = "<body><font face='Arial'>&yen; </font>";
            return Html.fromHtml(html).toString();
        } else if ("32".equals(type)) {
            // 美元符号
            return "$ ";
        }

        // 默认人民币
        String html = "<body><font face='Arial'>&yen; </font>";
        return Html.fromHtml(html).toString();
    }

    public static void hideSoftInput(Activity context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public static String getTime() {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String time = dff.format(new Date());
        if (time == null) {
            time = "";
        }
        return time;
    }

    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    public static String getScreenPx(Context context) {
        return "_" + context.getResources().getDisplayMetrics().widthPixels + "x" + context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * dp转换为px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * toast提示
     *
     * @param mContext
     * @param text
     */
    public static void sendToast(Context mContext, String text) {
        try {
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    /**
     * toast提示
     *
     * @param mContext
     * @param text
     */
    public static void sendLongToast(Context mContext, String text) {
        try {
            Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
            Looper.loop();
        }
    }

    ;

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static String getDeviceId(Context context) {
        String id = SPUtil.getString(context, Constant.CMB_DEVICES_ID_SP, "");
        if (TextUtils.isEmpty(id)) {
            id = getAndroidDeviceId(context);
            if (!TextUtils.isEmpty(id)) {
                SPUtil.putString(context, Constant.CMB_DEVICES_ID_SP, id);
            }
        }
        return id;
    }


    private static String getAndroidDeviceId(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String androidDeviceId = TelephonyMgr.getDeviceId();
        if (TextUtils.isEmpty(androidDeviceId)) {
            StringBuilder deviceId = new StringBuilder();
            try {
                //wifi mac地址
                WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifi.getConnectionInfo();
                String wifiMac = info.getMacAddress();
                if (!TextUtils.isEmpty(wifiMac)) {
                    deviceId.append("wifi");
                    deviceId.append(wifiMac);
                    return deviceId.toString();
                }
                //IMEI（imei）
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String imei = tm.getDeviceId();
                if (!TextUtils.isEmpty(imei)) {
                    deviceId.append("imei");
                    deviceId.append(imei);
                    return deviceId.toString();
                }
                //序列号（sn）
                String sn = tm.getSimSerialNumber();
                if (!TextUtils.isEmpty(sn)) {
                    deviceId.append("sn");
                    deviceId.append(sn);
                    return deviceId.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return deviceId.toString();
        } else {
            return androidDeviceId;
        }

    }

    public static HashMap<String, String> getCommomParameter() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Authorization", SPUtil.getString(LeaseApplication.mInstance, Constant.CMB_KEY_SP, ""));
        map.put("DeviceId", AndroidUtil.getDeviceId(LeaseApplication.mInstance));
        return map;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是wifi还是3g网络
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public static String getVersionName(Context mContext) {
        String versionCode = "";
        try {
            //获取软件版本号，
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取js版本号拼接参数
     *
     * @param context
     * @return 版本号拼接参数
     */
    public static String getVersionParam(Context context) {
        return "?VER=" + AndroidUtil.getVersionName(context);
    }

    /**
     * add by clk
     *
     * @param context
     * @return
     */
    public static boolean isGpsEnable(Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps) {
            return true;
        }
        return false;
    }

    /**
     * add by clk
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }

    /**
     * add by clk
     * 获取屏幕尺寸
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return new Point(display.getWidth(), display.getHeight());
        } else {
            Point point = new Point();
            display.getSize(point);
            return point;
        }
    }

    /**
     * 币种拼接工具类
     *
     * @param num
     * @param originString
     * @return
     */
    public static String getFinalString(String num, String originString) {
        if (num != null && !TextUtils.isEmpty(num)) {

            if ("未获取".equals(originString)) {
                return originString;
            } else if (num.equals("10") && !TextUtils.isEmpty(originString)) {
                return getMoneyTag() + originString;
            } else if (num.equals("32") && !TextUtils.isEmpty(originString)) {
                return "$" + originString;
            } else {
                return originString;
            }
        } else {
            return originString;
        }
    }

    /**
     * 币种工具类
     *
     * @param num
     * @return
     */
    public static String getMoneySign(String num) {
        if (num != null && !TextUtils.isEmpty(num)) {
            if (num.equals("10")) {
                return getMoneyTag();
            } else if (num.equals("32")) {
                return "$";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * @return
     */
    public static String getDateTime(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        String format = sdf.format(time);
        return format;
    }

    /**
     * 将时间转化成毫秒
     * 时间格式: yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static Long timeStrToSecond(String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long second = format.parse(time).getTime();
            return second;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1l;
    }

    /**
     * 验证数字格式  非负数 小数点后保留两位
     *
     * @param email
     * @return
     */
    public static boolean checkNum(String email) {
        boolean flag = false;
        try {
//            String check = "/^\\d+(\\.{0,1}\\d+){0,1}$/";
            String check = "^(([1-9]+)|([0-9]+\\.[0-9]{1,2}))$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * Java判断字符串是否为纯数字（0-9）
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        //字符串转成字符数组，在通过字符的方法判断
//        char[] chars = str.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            if (Character.isDigit(chars[i])) {
//                return false;
//            }
//        }

        return true;

    }

    /**
     * 为了解决ListView在ScrollView中只能显示一行数据的问题
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
