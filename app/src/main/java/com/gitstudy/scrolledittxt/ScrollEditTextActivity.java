package com.gitstudy.scrolledittxt;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gitstudy.R;
import com.gitstudy.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollEditTextActivity extends AppCompatActivity implements View.OnTouchListener {
    private static Activity mContext;
    @BindView(R.id.title)
    TextView mTvTitle;
    @BindView(R.id.edit)
    EditText mEdit;
    @BindView(R.id.btn_summit)
    public Button mBtnSubmit;
    @BindView(R.id.kb_layout)
    public KeyboardLayout mLayout;//外层包裹布局
    @BindView(R.id.scroll_view)
    public ScrollView mScrollView;
    @BindView(R.id.tv_text_num)
    TextView mTvTextNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_edit_text);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mContext=ScrollEditTextActivity.this;
        mTvTitle.setText("scroll_edit");

        mLayout.setOnkbdStateListener(new KeyboardLayout.onKeyboaddsChangeListener() {

            public void onKeyBoardStateChange(int state) {
                switch (state) {
                    case KeyboardLayout.KEYBOARD_STATE_HIDE://软键盘隐藏
                        mBtnSubmit.setVisibility(View.VISIBLE);
                        break;
                    case KeyboardLayout.KEYBOARD_STATE_SHOW://软键盘弹起
                        mBtnSubmit.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }
        });
        mEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mBtnSubmit.setVisibility(View.GONE);
                }
                return false;
            }
        });

        mEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("leikun", "内容改变之前调用:" + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("leikun", "内容改变，可以去告诉服务器:" + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("leikun", "内容改变之后调用:" + s.length());
                if (s.toString().length() > 10) {
                    ToastUtils.showShort(mContext, "最多输入10个字");
                    // mEdit.setText(mEditText);
                    String str = s.toString().substring(0, 10);
                    mEdit.setText(str);
                    mTvTextNum.setText("10/10");
                    return;
                }
                mTvTextNum.setText(s.toString().length() + "/10");
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(ScrollEditTextActivity.this);
                mBtnSubmit.setVisibility(View.VISIBLE);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    public void hideSoftInput(final Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
        if ((view.getId() == R.id.edit && canVerticalScroll(mEdit))) {
            view.getParent().requestDisallowInterceptTouchEvent(true);//告诉父view，我的事件自己处理
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.getParent().requestDisallowInterceptTouchEvent(false);//告诉父view，你可以处理了
            }
        }
        return false;
    }

    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动   false：不可以滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }
}
