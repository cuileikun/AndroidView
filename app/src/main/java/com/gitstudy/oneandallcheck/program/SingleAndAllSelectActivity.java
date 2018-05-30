package com.gitstudy.oneandallcheck.program;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gitstudy.R;
import com.gitstudy.pulltorefreshtest.secpullliarbry.PullToRefreshLayout;
import com.gitstudy.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * http://blog.csdn.net/zhongkejingwang/article/details/38868463
 */
public class SingleAndAllSelectActivity extends AppCompatActivity implements PullToRefreshLayout.OnRefreshListener {
    @BindView(R.id.lv)
    ListView mListView;
    @BindView(R.id.rl_all_select)
    RelativeLayout mRlAllSelect;
    @BindView(R.id.tv_commit_num)
    TextView mTvCommitNum;
    @BindView(R.id.iv_check_image)
    ImageView mIvCheckImage;
    @BindView(R.id.refresh_view)
    PullToRefreshLayout ptrl;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.tv_tip)
    TextView mTvTip;
    @BindView(R.id.tv_below_tip)
    TextView mTvBelowTip;
    @BindView(R.id.ll_bottom_tab)
    LinearLayout mLlBottomTab;
    private ProBatApproveAdapter mAdapter;
    private int checkNum = 0; // 记录选中的条目数量
    private boolean isLoad;//待办列表 是否可以上拉加载
    private boolean isRefresh;//待办列表 是否可以下拉刷新
    private String boundary = "";//2，待办列表  分页分隔符，为空表示首页
    private ArrayList<BatApproveListBean> mBatApprovelist;
    private ArrayList<ApproveOpnBean> mApproveOpnList;
    private String taskId = "";//任务唯一ID
    private String taskIds = "";//选中的待办TSK_ID，多个用英文逗号分隔
    private String checkId = "";//选中条目的任务id
    private boolean isCommitFinish;//从提交页面返回到批量审批列表的一个标志
    private ProBatApproveAdapter.ViewHolder holder;
    private boolean keepLoadSelectedState = false;//上拉加载之后，保存选中状态
    private Context mContext;


    /**
     * 所有批量审批任务均提交，3秒返回待办页面
     */
//    private void backToDoList() {
//        mTvTip.setVisibility(View.VISIBLE);
//        mTvBelowTip.setVisibility(View.VISIBLE);
//        mLlBottomTab.setVisibility(View.GONE);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mContext.setResult(20220);
//                mContext.finish();
//            }
//        }, 3000);
//
//    }
    private void makeAllUnSelected() {
        for (int i = 0; i < mBatApprovelist.size(); i++) {
            ProBatApproveAdapter.getIsSelected().put(i, false);
            mBatApprovelist.get(i).setItemSelected(false);
        }
        // 数量设为list的长度
        checkNum = 0;
        mIvCheckImage.setImageResource(R.drawable.return_visit_normal);
        dataChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_and_all_select);
        ButterKnife.bind(this);
        mContext = SingleAndAllSelectActivity.this;
        initView();
    }

    private void initView() {
        mTitle.setText("批量审批(0)");
        if (mBatApprovelist == null) {
            mBatApprovelist = new ArrayList<BatApproveListBean>();
        }
        if (mApproveOpnList == null) {
            mApproveOpnList = new ArrayList<ApproveOpnBean>();
        }

        getBatApproveData();
        mAdapter = new ProBatApproveAdapter(mBatApprovelist, this);
        ptrl.setOnRefreshListener(this);
        mListView.setAdapter(mAdapter);
        mRlAllSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allSelectedClick();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                holder = (ProBatApproveAdapter.ViewHolder) arg1.getTag();
                // 改变CheckBox的状态
                holder.cb.toggle();
                // 将CheckBox的选中状况记录下来

                ProBatApproveAdapter.getIsSelected().put(arg2, holder.cb.isChecked());
                mBatApprovelist.get(arg2).setItemSelected(holder.cb.isChecked());
                // 调整选定条目
                if (holder.cb.isChecked() == true) {
                    checkNum++;
                } else {
                    checkNum--;
                }
                if (checkNum == mBatApprovelist.size()) {
                    mIvCheckImage.setImageResource(R.drawable.return_visit_check);
                } else {
                    mIvCheckImage.setImageResource(R.drawable.return_visit_normal);
                }
                if (checkNum == 0) {
                    mTvCommitNum.setText("下一步");
                } else {
                    mTvCommitNum.setText("下一步(" + checkNum + ")");
                }
                mAdapter.notifyDataSetChanged();
                if (checkNum == 1) {
                    checkId = mBatApprovelist.get(arg2).getTSK_ID();
                    taskIds = checkId;
                } else if (checkNum > 1) {
                    checkId += "," + mBatApprovelist.get(arg2).getTSK_ID();
                    taskIds = checkId;
                }
            }
        });
    }

    /**
     * 全选点击事件逻辑处理
     */
    private void allSelectedClick() {
        if (checkNum == mBatApprovelist.size()) {
            //1,所有条目已经被选中了,再次点击 取消全选    遍历list的长度，将已选的按钮设为未选
            for (int i = 0; i < mBatApprovelist.size(); i++) {
                if (ProBatApproveAdapter.getIsSelected().get(i)) {
                    ProBatApproveAdapter.getIsSelected().put(i, false);
                    mBatApprovelist.get(i).setItemSelected(false);
                    checkNum--;
                }
            }
            taskIds = "";
            mIvCheckImage.setImageResource(R.drawable.return_visit_normal);
            // 刷新数据
            dataChanged();
        } else {
            //2,选中所有条目  遍历list的长度，将MyAdapter中的map值全部设为true
            for (int i = 0; i < mBatApprovelist.size(); i++) {
                ProBatApproveAdapter.getIsSelected().put(i, true);
                mBatApprovelist.get(i).setItemSelected(true);
            }
            if (mBatApprovelist.size() == 1) {
                checkId = mBatApprovelist.get(0).getTSK_ID();
                taskIds = checkId;
            } else if (mBatApprovelist.size() > 1) {
                checkId = mBatApprovelist.get(0).getTSK_ID();
                for (int i = 1; i < mBatApprovelist.size(); i++) {
                    checkId += "," + mBatApprovelist.get(i).getTSK_ID();
                    taskIds = checkId;
                }
            }
            // 数量设为list的长度
            checkNum = mBatApprovelist.size();
            mIvCheckImage.setImageResource(R.drawable.return_visit_check);
            dataChanged();
        }
    }

    private void dataChanged() {
        mAdapter.notifyDataSetChanged();
        if (checkNum == 0) {
            mTvCommitNum.setText("下一步");
        } else {
            mTvCommitNum.setText("下一步(" + checkNum + ")");
        }
    }

    @OnClick({R.id.left_btn, R.id.tv_commit_num})
    void click(View view) {
        switch (view.getId()) {
            case R.id.left_btn:
                finish();
                break;
            case R.id.tv_commit_num:
                if (checkNum == 0) {
                    ToastUtils.showShort(mContext, "至少选择一条要审批的事项哦");
                    return;
                }
                initApvopn();
                break;
        }
    }

    /**
     * 调用批量审批意见初始化接口
     */
    private void initApvopn() {
        for (int i = 0; i < 3; i++) {
            ApproveOpnBean oneBean = new ApproveOpnBean();
            oneBean.setOPN_ID("PA" + i);
            oneBean.setOPN_NAM("通过" + i);
            mApproveOpnList.add(oneBean);
        }

        jumpBatCommitActivity();
    }

    private void jumpBatCommitActivity() {
//        Intent intent = new Intent(mContext, BatCommitActivity.class);
//        intent.putExtra("mApproveOpnList", (Serializable) mApproveOpnList);//审批意见(PA\RP\CX\,,,)
//        intent.putExtra("mTaskId", taskIds);//选中条目对应的taskId
//        intent.putExtra("checkNum", checkNum);//选择的条数
//        intent.putExtra("nodid", nodId);//节点ID
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
    }

    /**
     * 联网批量审批列表
     */
    private void getBatApproveData() {
        isLoad = false;
        isRefresh = false;
        for (int i = 0; i < 10; i++) {
            BatApproveListBean oneBean = new BatApproveListBean();
            oneBean.setTSK_ID("T2008100700015022" + i);
            oneBean.setOPN_URL("prj.html" + i);
            oneBean.setNOD_NAM("营销报价" + i);
            oneBean.setNOD_ID("RT1" + i);
            oneBean.setRCV_DTE("1514959999632" + i);
            oneBean.setTIT_NAM("四川广播电视台" + i);
            oneBean.setLEV("1" + i);
            oneBean.setLEV_VAL("LEV_VAL" + i);
            oneBean.setLFT_COL("创建人" + i);
            oneBean.setLEV_VAL("杜宇轩" + i);
            oneBean.setRGT_COL("申请日期" + i);
            oneBean.setRGT_VAL("2018-01-09" + i);
            oneBean.setItemSelected(true);
            mBatApprovelist.add(oneBean);
        }
        boundary="6481248";
        doNext();
    }

    private void doNext() {

        mAdapter = new ProBatApproveAdapter(mBatApprovelist, mContext);
        mListView.setAdapter(mAdapter);
        mTitle.setText("批量审批");
        taskId = mBatApprovelist.get(0).getTSK_ID();
        //解决bug 上拉加载之后，没能正确定位到最后一条
        if (mBatApprovelist.size() > 20) {
            mListView.setSelection(mBatApprovelist.size());
        }
        //上拉加载之后保留选中状态
        if (keepLoadSelectedState) {
            for (int i = 0; i < mBatApprovelist.size(); i++) {
                if (mBatApprovelist.get(i).isItemSelected() == true) {
                    mAdapter.getIsSelected().put(i, true);
                } else {
                    mAdapter.getIsSelected().put(i, false);
                }
            }
            if (checkNum == mBatApprovelist.size()) {
                mIvCheckImage.setImageResource(R.drawable.return_visit_check);
            } else {
                mIvCheckImage.setImageResource(R.drawable.return_visit_normal);
            }
            mTvCommitNum.setText("下一步(" + checkNum + ")");
        } else {
            //重新刷新列表之后，全选状态清空 on
            checkNum = 0;
            mTvCommitNum.setText("下一步");
            mIvCheckImage.setImageResource(R.drawable.return_visit_normal);
            //重新刷新列表之后，全选状态清空 on
        }
        keepLoadSelectedState = false;
        mAdapter.notifyDataSetChanged();
//        onLoadFinish();
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        if (isLoad || isRefresh) {
            return;
        }
        boundary = "";
        isRefresh = true;
        getBatApproveData();
        // 下拉刷新操作
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 千万别忘了告诉控件刷新完毕了哦！
                ptrl.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 5000);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        if (isLoad || isRefresh) {
            return;
        }
        if (TextUtils.isEmpty(boundary)) {
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            return;
        }
        isLoad = true;
        keepLoadSelectedState = true;
        getBatApproveData();
        // 加载操作
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 千万别忘了告诉控件加载完毕了哦！
                ptrl.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 5000);
    }

//    /**
//     * 结束待办上下拉刷新
//     */
//    private void onLoadFinish() {
//        ptrl.refreshFinish(PullToRefreshLayout.SUCCEED);
////        ptrl.loadmoreFinish(PullToRefreshLayout.SUCCEED);
//    }

}
