package com.gitstudy.expandlistviewtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.gitstudy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 参考： 风险报告 fragment
 */
public class MultiItemExpandListViewActivity extends AppCompatActivity {

    @BindView(R.id.risk_report_expandable)
    ExpandableListView mRiskReportExpandable;
    public static final String TAG = "MultiItemExpandListViewActivity";

    private TextView tv_clt_nam;
    private TextView tv_ass_lev;
    private TextView tv_lea_amt;
    private TextView tv_rmn_cps;
    private TextView tv_rmn_mgn;
    private TextView tv_rsk_exp;
    private TextView tv_fst_lon_dte;
    private TextView tv_exp_dte;
    private TextView tv_clt_mng_nam;
    private TextView tv_clt_dpt;
    private TextView tv_clt_crd_lev;
    private TextView tv_has_flzz;
    private RiskReportBean riskReportBean;
    private List<RiskReportItemBean> riskReportItemList;
    private RiskReportAdapter mAdapter;
    private boolean isHideAdvice = false;//是否显示资产经理意见字段
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item_expand_list_view);
        mContext = MultiItemExpandListViewActivity.this;
        ButterKnife.bind(this);
        initHeaderView();
        initData();
    }

    private void initHeaderView() {
        View view = View.inflate(mContext,R.layout.fragment_risk_report_header, null);
        tv_clt_nam = (TextView) view.findViewById(R.id.tv_clt_nam);
        tv_ass_lev = (TextView) view.findViewById(R.id.tv_ass_lev);
        tv_lea_amt = (TextView) view.findViewById(R.id.tv_lea_amt);
        tv_rmn_cps = (TextView) view.findViewById(R.id.tv_rmn_cps);
        tv_rmn_mgn = (TextView) view.findViewById(R.id.tv_rmn_mgn);
        tv_rsk_exp = (TextView) view.findViewById(R.id.tv_rsk_exp);
        tv_fst_lon_dte = (TextView) view.findViewById(R.id.tv_fst_lon_dte);
        tv_exp_dte = (TextView) view.findViewById(R.id.tv_exp_dte);
        tv_clt_mng_nam = (TextView) view.findViewById(R.id.tv_clt_mng_nam);
        tv_clt_dpt = (TextView) view.findViewById(R.id.tv_clt_dpt);
        tv_clt_crd_lev = (TextView) view.findViewById(R.id.tv_clt_crd_lev);
        tv_has_flzz = (TextView) view.findViewById(R.id.tv_has_flzz);
        mRiskReportExpandable.addHeaderView(view);
    }

    private void initData() {
        mAdapter = new RiskReportAdapter(mContext);
        mRiskReportExpandable.setAdapter(mAdapter);
        //设置默认展开哪一项
//        mExpandableListView.expandGroup(0);
        mRiskReportExpandable.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
        mRiskReportExpandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                return false;
            }
        });
        //联网之后给界面控件赋值
        tv_clt_nam.setText("中国富康国际租赁有限公司");
        tv_ass_lev.setText("优良");
        tv_lea_amt.setText("$300.000.000");
        tv_rmn_cps.setText("$300.000.000");
        tv_rmn_mgn.setText("$300.000.000");
        tv_rsk_exp.setText("$300.000.000");
        tv_fst_lon_dte.setText("2017-10-10");
        tv_exp_dte.setText("2019-03-30");
        tv_clt_mng_nam.setText("张三");
        tv_clt_dpt.setText("设备租赁二部");
        tv_clt_crd_lev.setText("2C");
//        tv_has_flzz.setText(riskReportBean.HAS_FLZZ);
        mAdapter.setData(isHideAdvice, "10", riskReportItemList, riskReportBean);
        mAdapter.notifyDataSetChanged();

    }

}
