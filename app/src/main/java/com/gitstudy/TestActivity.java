package com.gitstudy;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.gitstudy.utils.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {
    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);

//        Uri uri = Uri.parse("content://media/external/images/media/539163");
        Uri uri = Uri.parse("content://media/external/video/media/734490");


        String realPath = "/storage/emulated/0/DCIM/Camera/IMG_20160807_133403.jpg";

        //test1 将时间转化成毫秒 时间格式: yyyy-MM-dd HH:mm:ss
        Long aLong = CommonUtil.timeStrToThird("31:48");
//        tv1.setText(aLong + "");

        //test2  Uri-->Path 在上传照片时遇到上传路径异常：content://media/external/images/media/539163   转成：/storage/emulated/0/DCIM/Camera/IMG_20160807_133403.jpg路径；
        String realPathFromUri = getRealPathFromUri(TestActivity.this, uri);
        if (TextUtils.isEmpty(realPathFromUri)) {
            realPathFromUri = "00";
        }
//        tv1.setText(realPathFromUri);
        //Path-->Uri
        Uri uriFromRealPath = getUriFromRealPath("/storage/emulated/0/DCIM/Camera/VID_20180823_163148.mp4");
        tv1.setText(String.valueOf(uriFromRealPath));


    }

    public String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public Uri getUriFromRealPath(String path) {
        Uri mUri = Uri.parse("content://media/external/video/media");
        Uri mImageUri = null;
//        Cursor cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Images.Media.DEFAULT_SORT_ORDER);
        Cursor cursor = managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Images.Media.DEFAULT_SORT_ORDER);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String data = cursor.getString(cursor
                    .getColumnIndex(MediaStore.MediaColumns.DATA));
            if (path.equals(data)) {
                int ringtoneID = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
                mImageUri = Uri.withAppendedPath(mUri, "" + ringtoneID);
                break;
            }
            cursor.moveToNext();
        }
        return mImageUri;
    }

}
