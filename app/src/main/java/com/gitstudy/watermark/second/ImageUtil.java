package com.gitstudy.watermark.second;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 图片工具类
 *
 * @author
 */
public class ImageUtil {

    private Paint paint;

    /**
     * 设置水印图片在左上角
     *
     * @param context
     * @param src
     * @param watermark
     * @param paddingLeft
     * @param paddingTop
     * @return
     */
    public static Bitmap createWaterMaskLeftTop(
            Context context, Bitmap src, Bitmap watermark,
            int paddingLeft, int paddingTop) {
        return createWaterMaskBitmap(src, watermark,
                dp2px(context, paddingLeft), dp2px(context, paddingTop));
    }

    private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark,
                                                int paddingLeft, int paddingTop) {
        if (src == null) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        //创建一个bitmap
        Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        //将该图片作为画布
        Canvas canvas = new Canvas(newb);
        //在画布 0，0坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);

        //在画布上绘制水印图片
        canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
        // 保存
        canvas.save(Canvas.ALL_SAVE_FLAG);
        // 存储
        canvas.restore();
        return newb;
    }

    /**
     * 设置水印图片在右下角
     *
     * @param context
     * @param src
     * @param watermark
     * @param paddingRight
     * @param paddingBottom
     * @return
     */
    public static Bitmap createWaterMaskRightBottom(
            Context context, Bitmap src, Bitmap watermark,
            int paddingRight, int paddingBottom) {
        return createWaterMaskBitmap(src, watermark,
                src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight),
                src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 设置水印图片到右上角
     *
     * @param context
     * @param src
     * @param watermark
     * @param paddingRight
     * @param paddingTop
     * @return
     */
    public static Bitmap createWaterMaskRightTop(
            Context context, Bitmap src, Bitmap watermark,
            int paddingRight, int paddingTop) {
        return createWaterMaskBitmap(src, watermark,
                src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight),
                dp2px(context, paddingTop));
    }

    /**
     * 设置水印图片到左下角
     *
     * @param context
     * @param src
     * @param watermark
     * @param paddingLeft
     * @param paddingBottom
     * @return
     */
    public static Bitmap createWaterMaskLeftBottom(
            Context context, Bitmap src, Bitmap watermark,
            int paddingLeft, int paddingBottom) {
        return createWaterMaskBitmap(src, watermark, dp2px(context, paddingLeft),
                src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 设置水印图片到中间
     *
     * @param context
     * @param src
     * @param watermark
     * @return
     */
    public static Bitmap createWaterMaskCenter(Context context, Bitmap src, Bitmap watermark) {
        float a = 0f;
        a = (float) src.getWidth() / AndroidUtil.getScreenWidth(context);
        watermark = scaleWithWH(watermark, a);
        return createWaterMaskBitmap(src, watermark,
                (src.getWidth() - watermark.getWidth()) / 2,
                (src.getHeight() - watermark.getHeight()) / 2);
    }

    /**
     * 给图片添加文字到左上角
     *
     * @param context
     * @param bitmap
     * @param text
     * @return
     */
    public static Bitmap drawTextToLeftTop(Context context, Bitmap bitmap, String text,
                                           int size, int color, int paddingLeft, int paddingTop) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                dp2px(context, paddingLeft),
                dp2px(context, paddingTop) + bounds.height());
    }

    /**
     * 绘制文字到右下角
     *
     * @param context
     * @param bitmap
     * @param text
     * @param size
     * @param color
     * @return
     */
    public static Bitmap drawTextToRightBottom(Context context, Bitmap bitmap, String text,
                                               int size, int color, int paddingRight, int paddingBottom) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                bitmap.getWidth() - bounds.width() - dp2px(context, paddingRight),
                bitmap.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 绘制文字到右上方
     *
     * @param context
     * @param bitmap
     * @param text
     * @param size
     * @param color
     * @param paddingRight
     * @param paddingTop
     * @return
     */
    public static Bitmap drawTextToRightTop(Context context, Bitmap bitmap, String text,
                                            int size, int color, int paddingRight, int paddingTop) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                bitmap.getWidth() - bounds.width() - dp2px(context, paddingRight),
                dp2px(context, paddingTop) + bounds.height());
    }

    /**
     * 绘制文字到左下方
     *
     * @param context
     * @param bitmap
     * @param text
     * @param size
     * @param color
     * @param paddingLeft
     * @param paddingBottom
     * @return
     */
    public static Bitmap drawTextToLeftBottom(Context context, Bitmap bitmap, String text,
                                              int size, int color, int paddingLeft, int paddingBottom) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(dp2px(context, size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds,
                dp2px(context, paddingLeft),
                bitmap.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 绘制文字到中间
     *
     * @param context
     * @param bitmap
     * @param strs
     * @param size
     * @param color
     * @return
     */
    public static Bitmap drawTextToCenter(Context context, Bitmap bitmap, ArrayList<String> strs,
                                          int size, int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        float a = 0f;
        //兼容华为手机PLK-AL10 (字体太小bug)
        if (!TextUtils.isEmpty(Build.MODEL) && "PLK-AL10".equals(Build.MODEL)) {
            a = (float) bitmap.getWidth() / AndroidUtil.getScreenWidth(context);
            int s = dp2px(context, size);
            a = (float) a * (float) s;
//            a=(float) 46.0;
            a = (float) 150.0;
            paint.setTextSize((int) a);
            // paint.setTextSize(dp2px(context, size));
        } else {
            if (AndroidUtil.getScreenWidth(context) > bitmap.getWidth()) {
                a = AndroidUtil.getScreenWidth(context) / (float) bitmap.getWidth();
                int s = dp2px(context, size);
                a = (float) s / (float) a;
                paint.setTextSize((int) a);
            } else {
                a = (float) bitmap.getWidth() / AndroidUtil.getScreenWidth(context);
                int s = dp2px(context, size);
                a = (float) a * (float) s;
                a = (float) 46.0;
                paint.setTextSize((int) a);
                // paint.setTextSize(dp2px(context, size));
            }
        }


        return drawTextToBitmap2(context, bitmap, paint, strs, size);
    }

    public static Bitmap drawTextToCenter3(Context context, Bitmap bitmap, ArrayList<String> strs,
                                           int size, int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        float a = 0f;
        if (AndroidUtil.getScreenWidth(context) > bitmap.getWidth()) {
            a = AndroidUtil.getScreenWidth(context) / (float) bitmap.getWidth();
            int s = dp2px(context, size);
            a = (float) s / (float) a;
            paint.setTextSize((int) a);
        } else {
            a = (float) bitmap.getWidth() / AndroidUtil.getScreenWidth(context);
            int s = dp2px(context, size);
            a = (float) a * (float) s;
            paint.setTextSize((int) a);
        }
        return drawTextToBitmap3(context, bitmap, paint, strs);
    }

    //图片上绘制文字
    private static Bitmap drawTextToBitmap2(Context context, Bitmap bitmap,
                                            Paint paint, ArrayList<String> strs, int size) {
        Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
//        int paddingLeft2 = 0;
        float a = 0f;
//        if(AndroidUtil.getScreenWidth(context)> bitmap.getWidth()){
//            a = AndroidUtil.getScreenWidth(context)/(float)bitmap.getWidth();
//            int s = AndroidUtil.dp2px(context,20);
//            a = (float) s/(float) a;
//        }else {
//            a = (float)bitmap.getWidth()/AndroidUtil.getScreenWidth(context);
//            int s = AndroidUtil.dp2px(context,20);
//            a = (float) a*(float) s;
//        }
        int paddingTop = 0;//bitmap.getHeight() / 2;
//        if(AndroidUtil.getScreenWidth(context)> bitmap.getWidth()){
//            a = AndroidUtil.getScreenWidth(context)/(float)bitmap.getWidth();
//            int s = AndroidUtil.dp2px(context,60);
//            a = (float) s/(float) a;
//        }else {
//            a = (float)bitmap.getWidth()/AndroidUtil.getScreenWidth(context);
//            int s = AndroidUtil.dp2px(context,60);
//            a = (float) a*(float) s;
//        }
        //    paddingTop+=(int) a;
        //     Bitmap bitmap2 = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);
        canvas.save();
        canvas.rotate(-45, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        //canvas.translate(-AndroidUtil.dp2px(context,300),AndroidUtil.dp2px(context,150));
        int top = 0;
        //兼容华为手机PLK-AL10 (字体太小bug)
        if (!TextUtils.isEmpty(Build.MODEL) && "PLK-AL10".equals(Build.MODEL)) {
            a = (float) bitmap.getWidth() / AndroidUtil.getScreenWidth(context);
            int s = AndroidUtil.dp2px(context, 40);
            a = (float) a * (float) s;
//            a = (float) 80.0;
            a = (float) 260.0;
        } else {
            if (AndroidUtil.getScreenWidth(context) > bitmap.getWidth()) {
                a = AndroidUtil.getScreenWidth(context) / (float) bitmap.getWidth();
                int s = AndroidUtil.dp2px(context, 40);
                a = (float) s / (float) a;
            } else {
                a = (float) bitmap.getWidth() / AndroidUtil.getScreenWidth(context);
                int s = AndroidUtil.dp2px(context, 40);
                a = (float) a * (float) s;
                a = (float) 80.0;
            }
        }


        top = (int) a;
        int paddingLeft = 0;
        Rect bounds = new Rect();
        paint.getTextBounds("中公2102", 0, "中公2102".length(), bounds);
        paddingTop = (bitmap.getHeight() + bounds.height() * strs.size() + top * (strs.size() - 1)) / 2;

        for (int i = 0; i < strs.size(); i++) {
            if (!TextUtils.isEmpty(strs.get(i))) {
                paint.getTextBounds(strs.get(i), 0, strs.get(i).length(), bounds);
                paddingLeft = (bitmap.getWidth() - bounds.width()) / 2;
                canvas.drawText(strs.get(i), paddingLeft, paddingTop, paint);
                paddingTop = paddingTop - top;
            }
        }
//        if(!TextUtils.isEmpty(mdres)){
//            canvas.drawText(mdres, paddingLeft, paddingTop, paint);
//        }
        canvas.restore();
        return bitmap;
    }

    //图片上绘制文字
    private static Bitmap drawTextToBitmap3(Context context, Bitmap bitmap,
                                            Paint paint, ArrayList<String> strs) {
        Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
//        int paddingLeft = 0;
//        float a= 0f;
//        if(AndroidUtil.getScreenWidth(context)> bitmap.getWidth()){
//            a = AndroidUtil.getScreenWidth(context)/(float)bitmap.getWidth();
//            int s = AndroidUtil.dp2px(context,35);
//            a = (float) s/(float) a;
//        }else {
//            a = (float)bitmap.getWidth()/AndroidUtil.getScreenWidth(context);
//            int s = AndroidUtil.dp2px(context,35);
//            a = (float) a*(float) s;
//        }
//        paddingLeft = (int) a;
//        int paddingTop = bitmap.getHeight() / 2;
//        if(AndroidUtil.getScreenWidth(context)> bitmap.getWidth()){
//            a = AndroidUtil.getScreenWidth(context)/(float)bitmap.getWidth();
//            int s = AndroidUtil.dp2px(context,60);
//            a = (float) s/(float) a;
//        }else {
//            a = (float)bitmap.getWidth()/AndroidUtil.getScreenWidth(context);
//            int s = AndroidUtil.dp2px(context,60);
//            a = (float) a*(float) s;
//        }
//        paddingTop+=(int) a;
//        int top = 0;
//        if(AndroidUtil.getScreenWidth(context)> bitmap.getWidth()){
//            a = AndroidUtil.getScreenWidth(context)/(float)bitmap.getWidth();
//            int s = AndroidUtil.dp2px(context,40);
//            a = (float) s/(float) a;
//        }else {
//            a = (float)bitmap.getWidth()/AndroidUtil.getScreenWidth(context);
//            int s = AndroidUtil.dp2px(context,40);
//            a = (float) a*(float) s;
//        }
//        top = (int)a;
        //        int paddingLeft2 = 0;
        float a = 0f;
//        if(AndroidUtil.getScreenWidth(context)> bitmap.getWidth()){
//            a = AndroidUtil.getScreenWidth(context)/(float)bitmap.getWidth();
//            int s = AndroidUtil.dp2px(context,20);
//            a = (float) s/(float) a;
//        }else {
//            a = (float)bitmap.getWidth()/AndroidUtil.getScreenWidth(context);
//            int s = AndroidUtil.dp2px(context,20);
//            a = (float) a*(float) s;
//        }
        int paddingTop = 0;//bitmap.getHeight() / 2;
//        if(AndroidUtil.getScreenWidth(context)> bitmap.getWidth()){
//            a = AndroidUtil.getScreenWidth(context)/(float)bitmap.getWidth();
//            int s = AndroidUtil.dp2px(context,60);
//            a = (float) s/(float) a;
//        }else {
//            a = (float)bitmap.getWidth()/AndroidUtil.getScreenWidth(context);
//            int s = AndroidUtil.dp2px(context,60);
//            a = (float) a*(float) s;
//        }
        //    paddingTop+=(int) a;
        //     Bitmap bitmap2 = bitmap.copy(bitmapConfig, true);
        //canvas.translate(-AndroidUtil.dp2px(context,300),AndroidUtil.dp2px(context,150));
        int top = 0;
        if (AndroidUtil.getScreenWidth(context) > bitmap.getWidth()) {
            a = AndroidUtil.getScreenWidth(context) / (float) bitmap.getWidth();
            int s = AndroidUtil.dp2px(context, 40);
            a = (float) s / (float) a;
        } else {
            a = (float) bitmap.getWidth() / AndroidUtil.getScreenWidth(context);
            int s = AndroidUtil.dp2px(context, 40);
            a = (float) a * (float) s;
        }
        top = (int) a;
        int paddingLeft = 0;
        Rect bounds = new Rect();
        paint.getTextBounds("中公2102", 0, "中公2102".length(), bounds);
        paddingTop = (bitmap.getHeight() + bounds.height() * strs.size() + top * (strs.size() - 1)) / 2;
        // Bitmap bitmap2 = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);
        canvas.save();
        canvas.rotate(-45, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        for (int i = 0; i < strs.size(); i++) {
            if (!TextUtils.isEmpty(strs.get(i))) {
                paint.getTextBounds(strs.get(i), 0, strs.get(i).length(), bounds);
                paddingLeft = (bitmap.getWidth() - bounds.width()) / 2;
                canvas.drawText(strs.get(i), paddingLeft, paddingTop, paint);
                paddingTop = paddingTop - top;
            }
        }
        canvas.restore();
        return bitmap;
    }

    //图片上绘制文字
    private static Bitmap drawTextToBitmap(Context context, Bitmap bitmap, String text,
                                           Paint paint, Rect bounds, int paddingLeft, int paddingTop) {
        Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        Bitmap bitmap2 = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap2);
        canvas.save();
        canvas.drawText(text, paddingLeft, paddingTop, paint);
        //canvas.restore();
        bitmap.recycle();
        bitmap = null;
        return bitmap2;
    }

    /**
     * 缩放图片
     *
     * @param src
     * @param w
     * @param h
     * @return
     */
    public static Bitmap scaleWithWH2(Bitmap src, double w, double h) {
        if (w == 0 || h == 0 || src == null) {
            return src;
        } else {
            // 记录src的宽高
            int width = src.getWidth();
            int height = src.getHeight();
            // 创建一个matrix容器
            Matrix matrix = new Matrix();
            // 计算缩放比例
            float scaleWidth = (float) (w / width);
            float scaleHeight = (float) (h / height);
            // 开始缩放
            matrix.postScale(scaleWidth, scaleHeight);
            // 创建缩放后的图片
            return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
        }
    }

    public static Bitmap scaleWithWH(Bitmap src, float w) {
        if (w == 0 || src == null) {
            return src;
        } else {
            // 记录src的宽高
            int width = src.getWidth();
            int height = src.getHeight();
            // 创建一个matrix容器
            Matrix matrix = new Matrix();
            // 计算缩放比例
            //         float scaleWidth = (float) (w / width);
            //      float scaleHeight = (float) (h / height);
            // 开始缩放
            matrix.postScale(w, w);
            // 创建缩放后的图片
            return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
        }
    }


    /**
     * dip转pix
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @TargetApi(16)
    public static void centerTextBackGround(Context context, ViewGroup viewGroup) {
        if (viewGroup != null) {
            try {
                ArrayList<String> strs = new ArrayList<>();
                strs.add("上海市浦东新区陆家嘴环路1504号");//地址
                strs.add("202753/方坤");//姓名
                if (!TextUtils.isEmpty(AndroidUtil.getTime())) {//当前时间
                    strs.add(AndroidUtil.getTime());
                }
                WaterMarkBg waterMarkBg = new WaterMarkBg(context, strs, -30, 13, false);
                viewGroup.setBackground(waterMarkBg);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}
