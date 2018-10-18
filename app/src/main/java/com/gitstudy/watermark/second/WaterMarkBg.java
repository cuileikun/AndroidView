package com.gitstudy.watermark.second;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 *
 */

public class WaterMarkBg extends Drawable {

    private Paint paint = new Paint();
    private List<String> labels;
    private Context context;
    private int degress;//角度
    private int fontSize;//字体大小 单位sp

    private boolean singleCenter = false;//是否居中显示一个

    /**
     * 初始化构造
     *
     * @param context  上下文
     * @param labels   水印文字列表 多行显示支持
     * @param degress  水印角度
     * @param fontSize 水印文字大小
     */
    public WaterMarkBg(Context context, List<String> labels, int degress, int fontSize) {
        this.labels = labels;
        this.context = context;
        this.degress = degress;
        this.fontSize = fontSize;
    }

    public WaterMarkBg(Context context, List<String> labels, int degress, int fontSize, boolean singleCenter) {
        this.labels = labels;
        this.context = context;
        this.degress = degress;
        this.fontSize = fontSize;
        this.singleCenter = singleCenter;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {


        int width = getBounds().right;
        int height = getBounds().bottom;

        canvas.drawColor(Color.parseColor("#00000000"));
        paint.setColor(Color.parseColor("#50DC134C"));//设置水印字体颜色

        paint.setAntiAlias(true);
        paint.setTextSize(sp2px(context, fontSize));
        canvas.save();
        canvas.rotate(degress);
        float textWidth = paint.measureText(labels.get(0));

        int index = 0;

        if (singleCenter == false) {

            for (int positionY = height / 10; positionY <= height; positionY += height / 10 + 80) {
                float fromX = -width + (index++ % 2) * textWidth;
                for (float positionX = fromX; positionX < width; positionX += textWidth * 2) {
                    int spacing = 0;//间距
                    for (int i = 0; i < labels.size(); i++) {
                        //保证文字居中对齐而不是左对齐
                        if (paint.measureText(labels.get(i)) >= textWidth) {
                            canvas.drawText(labels.get(i), positionX - (paint.measureText(labels.get(i)) - textWidth) / 2, positionY + spacing, paint);
                        } else {
                            canvas.drawText(labels.get(i), positionX + (textWidth - paint.measureText(labels.get(i))) / 2, positionY + spacing, paint);
                        }


                        spacing = spacing + 50;
                    }
                }
            }
        } else {
            int spacing = 5;//间距
            int positionY = height / 2;//间距
            for (int i = 0; i < labels.size(); i++) {
                textWidth = paint.measureText(labels.get(i));
                canvas.drawText(labels.get(i), (width - textWidth) / 2, positionY + spacing, paint);
                spacing = spacing + 50;
            }

        }


        canvas.restore();
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }


    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
