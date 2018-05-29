package com.gitstudy.popupwindow.custompop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gitstudy.utils.CommonUtil;

/**
 * Created by mbcloud-wangch on 2018/3/16.
 */

public class QBgView extends View {

    private int w;
    private int h;
    private Paint paint;
    private RectF rectF;
    private Context context;
    private Path path;

    public QBgView(Context context) {
        super(context);
        init(context);
    }

    public QBgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public QBgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modew  = MeasureSpec.getMode(widthMeasureSpec);
        int modeh  = MeasureSpec.getMode(heightMeasureSpec);
        int cw = MeasureSpec.getSize(widthMeasureSpec);
        int ch = MeasureSpec.getSize(heightMeasureSpec);
        switch (modew){
            case MeasureSpec.EXACTLY:
                w = cw;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        switch (modeh){
            case MeasureSpec.EXACTLY:
                h = ch;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        setMeasuredDimension(w,h);
    }

    private void init(Context context){
        this.context = context;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0x8f000000);
     //   paint.setARGB(127,0,0,0);
        path = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w1 = 0;//w-AndroidUtil.dp2px(context,36);
        path.reset();
        path.moveTo(w1, CommonUtil.dp2px(context,12));
        path.lineTo(w1+CommonUtil.dp2px(context,8),0);
        path.lineTo(w1+CommonUtil.dp2px(context,16),CommonUtil.dp2px(context,12));
        path.close();
       // rectF = new RectF(0,AndroidUtil.dp2px(context,12),w,h);
      //  canvas.drawRoundRect(rectF, AndroidUtil.dp2px(context,3),AndroidUtil.dp2px(context,3),paint);
        canvas.drawPath(path,paint);
    }
}
