package com.gitstudy.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gitstudy.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 原文链接：http://blog.csdn.net/a_zhon/article/details/54578047
 */
public class DialogActivity extends AppCompatActivity implements View.OnClickListener{
    private Button normal;
    private Button single;
    private Button halfCustom;
    private Button custom;
    private Button item;
    private Button multi;
    private Button circleProgress;
    private Button lineProgress;
    private Button bottomSheet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
        initListener();
    }

    /**
     * 初始化控件监听
     */
    private void initListener() {
        normal.setOnClickListener(this);
        single.setOnClickListener(this);
        halfCustom.setOnClickListener(this);
        custom.setOnClickListener(this);
        item.setOnClickListener(this);
        multi.setOnClickListener(this);
        circleProgress.setOnClickListener(this);
        lineProgress.setOnClickListener(this);
        bottomSheet.setOnClickListener(this);
    }

    /**
     * 寻找布局控件
     */
    private void initView() {
        normal = (Button) findViewById(R.id.dialog_normal);
        single = (Button) findViewById(R.id.dialog_single);
        halfCustom = (Button) findViewById(R.id.dialog_half_custom);
        custom = (Button) findViewById(R.id.dialog_custom);
        item = (Button) findViewById(R.id.dialog_item);
        multi = (Button) findViewById(R.id.dialog_multi);
        circleProgress = (Button) findViewById(R.id.dialog_progress);
        lineProgress = (Button) findViewById(R.id.dialog_progress_line);
        bottomSheet = (Button) findViewById(R.id.dialog_bottom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_normal:
                NormalDialogTwo();
                break;
            case R.id.dialog_item:
                itemDialog();
                break;
            case R.id.dialog_single:
                singleDialog();
                break;
            case R.id.dialog_multi:
                multiDialog();
                break;
            case R.id.dialog_half_custom:
                halfCustomDialog();
                break;
            case R.id.dialog_custom:
                customDialog();
//                customBottomDialog();
                break;
            case R.id.dialog_progress:
                circleProgressDialog();
                break;
            case R.id.dialog_progress_line:
                lineProgressDialog();
                break;
            case R.id.dialog_bottom:
                bottomSheetDialog();
                break;
        }
    }

    /**
     * 普通对话框
     * 两个按钮
     */
    private void NormalDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.icon)//设置标题的图片
                .setTitle("我是对话框")//设置对话框的标题
                .setMessage("我是对话框的内容")//设置对话框的内容
                //设置对话框的按钮,NegativeButton这个按钮是在对话框的左边,PositiveButton实在对话框的右边
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 普通对话框
     * 三个按钮
     */
    private void NormalDialogTwo() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.icon)//设置标题的图片
                .setTitle("普通对话框")//设置对话框的标题
                .setMessage("我是对话框的内容")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("第三个按钮", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "点击了第三个按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 列表对话框
     */
    private void itemDialog() {
        final String items[] = {"我是Item一", "我是Item二", "我是Item三", "我是Item四"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.icon)//设置标题的图片
                .setTitle("列表对话框")//设置对话框的标题
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 单选列表对话框
     */
    private void singleDialog() {
        final String items[] = {"取消", "关闭APP", "退出登录"};
        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setIcon(R.mipmap.icon)//设置标题的图片
                .setTitle("关闭APP或退出登录")//设置对话框的标题
                //第一个参数:设置单选的资源;第二个参数:设置默认选中哪一项;
                .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 多选对话框
     */
    private void multiDialog() {
        final String items[] = {"我是Item一", "我是Item二", "我是Item三", "我是Item四"};
        final boolean checkedItems[] = {true, false, true, false};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.icon)//设置标题的图片
                .setTitle("多选对话框")//设置对话框的标题
                //第一个参数:设置单选的资源;第二个参数:设置默认选中哪几项;
                .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = isChecked;
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {
                                Toast.makeText(DialogActivity.this, "选中了" + i, Toast.LENGTH_SHORT).show();
                            }
                        }
                        dialog.dismiss();
                    }

                }).create();

        dialog.show();
    }

    /**
     * 半自定义对话框
     */
    private void halfCustomDialog() {
        View view = getLayoutInflater().inflate(R.layout.half_dialog_view, null);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.icon)//设置标题的图片
                .setTitle("半自定义对话框")//设置对话框的标题
                .setView(view)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = editText.getText().toString();
                        Toast.makeText(DialogActivity.this, content, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 自定义对话框
     */
    private void customDialog() {
        final Dialog dialog = new Dialog(this, R.style.NormalDialogStyle);
        View view = View.inflate(this, R.layout.dialog_normal, null);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        TextView confirm = (TextView) view.findViewById(R.id.confirm);
        dialog.setContentView(view);
        //使得点击对话框外部不消失对话框
        dialog.setCanceledOnTouchOutside(true);
        //设置对话框的大小
        view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight() * 0.23f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(this).getScreenWidth() * 0.75f);//设置对话框的宽度
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;//设置对话框的高度
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 自定义底部对话框
     */
    private void customBottomDialog() {
        /**
         *
         */
        Dialog dialog = new Dialog(this, R.style.NormalDialogStyle);
        View view = View.inflate(this, R.layout.dialog_bottom, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight() * 0.23f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(this).getScreenWidth() * 0.9f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    /**
     * 圆形进度条对话框
     */
    private void circleProgressDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("正在加载中");
        dialog.show();
    }

    /**
     * 直线进度条对话框
     */
    private void lineProgressDialog() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("正在加载中");
        dialog.setMax(100);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int progress = 0;

            @Override
            public void run() {
                dialog.setProgress(progress += 5);
                if (progress == 100) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
        dialog.show();
    }

    /**
     * 可以拖动的底部对话框
     */
    private void bottomSheetDialog() {
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        dialog.setContentView(view);
        //默认展开对话框
        final FrameLayout frameLayout = (FrameLayout) dialog.findViewById(android.support.design.R.id.design_bottom_sheet);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                BottomSheetBehavior behavior = BottomSheetBehavior.from(frameLayout);
                behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN)
                            dialog.dismiss();
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                    }
                });
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);//展开
            }
        });
        dialog.show();
    }




}
