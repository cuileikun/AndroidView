package com.qk.applibrary.widget;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.qk.applibrary.BuildConfig;
import com.qk.applibrary.R;
import com.qk.applibrary.util.CommonUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * app更新进度框
 */
public class AppUpdateDialog {
    ProgressBar progressBar;//下载进度条
    /* 记录进度条数量 */
    private int progress;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;
    private Dialog mDownloadDialog;
    private String apkName;
    private String downloadUrl;
    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    private static final int NETWORK_ERROR = 3;
    private Context context;
    private AppUpdateListener appUpdateListener;
    private String mSaveApkPath;//保存apk路径

    /**
     * 更新进度框
     *
     * @param context          上下文
     * @param updateListener   更新回调接口
     * @param downloadUrl      下载地址
     * @param showCancelButton 是不是显示取消按扭
     * @param saveApkPath      保存apk路径
     * @param forceUpdateFlag
     */
    public AppUpdateDialog(Context context, final AppUpdateListener updateListener, String downloadUrl, boolean showCancelButton, String saveApkPath, boolean forceUpdateFlag) {
        this.context = context;
        this.appUpdateListener = updateListener;
        this.downloadUrl = downloadUrl;
        mSaveApkPath = saveApkPath;
        final LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.update_progress, null);
        progressBar = (ProgressBar) v.findViewById(R.id.update_progress_pb);
        if (forceUpdateFlag) {
            // 构造软件下载对话框
            Builder builder = new Builder(context);
            builder.setTitle(R.string.soft_updating);
            // 给下载对话框增加进度条
            builder.setView(v);
            if (showCancelButton) {
                // 取消更新
                builder.setNegativeButton(R.string.soft_update_cancel, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        cancelUpdate = true;
                        if (appUpdateListener != null) {
                            appUpdateListener.cancelUpdate();
                        }
                    }
                });
            }
            mDownloadDialog = builder.create();
            mDownloadDialog.show();

        }
        new DownloadApkThread().start();

    }

    public interface AppUpdateListener {
        public void cancelUpdate();

        public void updateError(String error);
    }

    ;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    progressBar.setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    // 安装文件
                    installApk();
                    break;
                case NETWORK_ERROR:
//				openNetErrorDialog(new NetErrorCallback() {
//					public void retry() {
//						new OpenPushService().start();		
//					}
//				});
                    if (appUpdateListener != null) {
                        appUpdateListener.updateError((String) msg.obj);
                    }
                    if (mDownloadDialog != null)
                        mDownloadDialog.dismiss();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 安装APK文件
     */
//	private void installApk()
//	{
//		if(mDownloadDialog != null)
//			mDownloadDialog.dismiss();
//		File apkfile = new File(mSaveApkPath, apkName);
//		if (!apkfile.exists())
//		{
//			return;
//		}
////		// 通过Intent安装APK文件
////		Intent i = new Intent(Intent.ACTION_VIEW);
////		i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
////		context.startActivity(i);
//
//
//		Intent intent = new Intent(Intent.ACTION_VIEW);
////		判断是否是AndroidN以及更高的版本
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//			intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//			Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", apkFile);
//			intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
//		} else {
//			intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		}
//		context.startActivity(intent);
//
//
//
//
//
//
//
//		/**
//		 * 结束欢迎界面 防止到安装界面点击取消卡在欢迎界面
//		 */
//		((Activity)context).finish();
//	}

    /**
     * 安装APK文件
     */
    private void installApk() {
        if (mDownloadDialog != null)
            mDownloadDialog.dismiss();
        File apkFile = new File(mSaveApkPath, apkName);
        if (!apkFile.exists()) {
            return;
        }
//		// 通过Intent安装APK文件
//		Intent i = new Intent(Intent.ACTION_VIEW);
//		i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
//		context.startActivity(i);
        /**
         * 结束欢迎界面 防止到安装界面点击取消卡在欢迎界面
         */

        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, "com.qk365.housekeeper.fileprovider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);

        /**
         * 结束欢迎界面 防止到安装界面点击取消卡在欢迎界面
         */
        ((Activity) context).finish();
    }


    /**
     * 下载文件线程
     *
     * @author coolszy
     * @date 2012-4-26
     * @blog http://blog.92coding.com
     */
    private class DownloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    apkName = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1,
                            downloadUrl.length());
                    URL url = new URL(downloadUrl);
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(mSaveApkPath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }

                    File apkFile = new File(mSaveApkPath, apkName);
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0) {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                } else {
                    Message msg = mHandler.obtainMessage(NETWORK_ERROR);
                    msg.obj = "没有SD卡";
                    mHandler.sendMessage(msg);
                }
            } catch (Exception e) {
                Message msg = mHandler.obtainMessage(NETWORK_ERROR);
                msg.obj = "下载失败";
                mHandler.sendMessage(msg);
            }
        }
    }

    ;


}
