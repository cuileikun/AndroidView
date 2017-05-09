package com.qk.applibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qk.applibrary.R;
import com.qk.applibrary.listener.TopbarImplListener;
import org.apache.commons.lang.StringUtils;
/**
 * 顶部头部条
 */
public class TopbarView extends RelativeLayout {
    private  TextView topbarTitleSupplementTv;//标题的附加信息文本
    private TextView topbarTitleTv;
    private Button topbarRightBt;
    private TextView topbarBackTv;
    private ImageView topbarBackIv;
    private RelativeLayout topbarLeftRl;
    private View topView;

    public TopbarImplListener listener;

	public TopbarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater mInflater = LayoutInflater.from(context);
        topView = mInflater.inflate(R.layout.top_bar, this,
				false);
		topbarTitleTv = (TextView)topView.findViewById(R.id.top_bar_title_tv);
		topbarTitleSupplementTv = (TextView)topView.findViewById(R.id.top_bar_title_supplement_tv);
        topbarRightBt = (Button)topView.findViewById(R.id.top_bar_right_bt);
        topbarBackTv = (TextView)topView.findViewById(R.id.top_bar_back_tv);
        topbarBackIv = (ImageView)topView.findViewById(R.id.top_bar_back_iv);
        topbarLeftRl = (RelativeLayout)topView.findViewById(R.id.top_bar_left_rl);
        /**
         * 点击左边按扭事件
         */
        topbarLeftRl.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(listener != null) {
                    listener.leftButtonClick();
                }

            }
        });

        /**
         * 点击右边按扭事件
         */
        topbarRightBt.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(listener != null) {
                    listener.rightButtonClick();
                }
            }
        });

		addView(topView);
	}

    /**
     * 返回文本框
     * @return
     */
    public TextView getTopbarBackTv() {
        return topbarBackTv;
    }

    /**
     * 返回图片框
     * @return
     */
    public ImageView getTopbarBackIv() {
        return topbarBackIv;
    }

    public Button getTopbarRightBt() {
        return topbarRightBt;
    }

    /**
     * 设置top中间文本的补充文字
     * @param supplementText
     */
	public void setTopbarSupplementTitle(String supplementText) {
		if(StringUtils.isNotEmpty(supplementText)) {
            if (topbarTitleSupplementTv.getVisibility()!=View.VISIBLE){
                topbarTitleSupplementTv.setVisibility(View.VISIBLE);
            }
            topbarTitleSupplementTv.setText(supplementText);
		}
	}

    /**
     * 设置top中间文字
     * @param title
     */
	public void setTopbarTitle(String title) {
		if(StringUtils.isNotEmpty(title)) {
			topbarTitleTv.setText(title);
		}
	}

    public View getTopbarView() {
        return topView;
    }

    /**
     * 设置top bar事件
     * @param listener
     */
    public void setTopBarClickListener(TopbarImplListener listener){
        this.listener = listener;
    }
    

}
