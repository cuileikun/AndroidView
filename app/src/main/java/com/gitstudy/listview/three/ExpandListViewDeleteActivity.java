package com.gitstudy.listview.three;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gitstudy.R;
import com.gitstudy.expandlistviewtest.RiskReportBean;
import com.gitstudy.expandlistviewtest.RiskReportItemBean;
import com.gitstudy.listview.swipedeletelib.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 知识点：ExpandableListView加载多条目 隐藏条目  侧滑删除条目
 */
public class ExpandListViewDeleteActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_expand_list_view_delete);
        mContext = ExpandListViewDeleteActivity.this;
        ButterKnife.bind(this);
        initHeaderView();
        initData();
    }

    private void initHeaderView() {
        View view = View.inflate(mContext, R.layout.fragment_risk_report_header, null);
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
        NewProjectChangeApproveBean mAdviceData = new NewProjectChangeApproveBean();
        for (int i = 0; i < 6; i++) {
            ProjectChangeApproveListBean bean = new ProjectChangeApproveListBean();
            bean.CMS_USR = "崔宏伟" + i;
            bean.CMS_STS = "通过" + i;
            bean.MEM_DTL = "同意" + i;
            mAdviceData.approveList.add(bean);
        }

        mAdapter.setData(isHideAdvice, "10", riskReportItemList, riskReportBean, mAdviceData);
        mAdapter.notifyDataSetChanged();

    }

    /**
     * Created by mbcloud-cuilk on 2018/1/22.
     * 风险报告适配器
     */
    public class RiskReportAdapter extends BaseExpandableListAdapter {
        public String[] group = {"业务概况", "风险事项报告", "资产经理意见"};
        public String[] secondGroup = {"业务概况", "风险事项报告"};

        String[][] child = {{""}, {""}, {""}};
        String[][] secondChild = {{""}, {""}};
        LayoutInflater mInflater;
        Context context;
        private RiskReportBean riskReportBean;
        private List<RiskReportItemBean> riskReportItemList;
        private String mCurNum;
        private boolean isHideAdvice = false;//是否显示资产经理意见字段
        private final android.os.Handler handler;
        private NewProjectChangeApproveBean mAdviceData;
        private ArrayList<ProjectChangeApproveListBean> approveList;

        public void setData(boolean isHideAdvice, String mCurNum, List<RiskReportItemBean> riskReportItemList, RiskReportBean riskReportBean, NewProjectChangeApproveBean mAdviceData) {
            this.isHideAdvice = isHideAdvice;
            this.mCurNum = mCurNum;
            this.riskReportItemList = riskReportItemList;
            this.riskReportBean = riskReportBean;
            this.mAdviceData = mAdviceData;
        }

        public RiskReportAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
            handler = new android.os.Handler() {
                @Override
                public void handleMessage(Message msg) {
                    notifyDataSetChanged();
                    super.handleMessage(msg);
                }
            };
        }

        public void refresh() {
            handler.sendMessage(new Message());
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            if (isHideAdvice) {
                return secondChild[groupPosition][childPosition];
            } else {
                return child[groupPosition][childPosition];
            }
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (isHideAdvice) {
                int type = getChildType(groupPosition, childPosition);
                if (groupPosition == 0) {
                    if (type == 0) {
                        ConstantViewHolder holder;
                        if (convertView == null) {
                            holder = new ConstantViewHolder();
                            convertView = LayoutInflater.from(context).inflate(R.layout.risk_data_constant_item, null);
                            holder.ll_content = (LinearLayout) convertView.findViewById(R.id.ll_content);
                            holder.tv_fee_typ = (TextView) convertView.findViewById(R.id.tv_fee_typ);
                            holder.tv_fee_pln_amt = (TextView) convertView.findViewById(R.id.tv_fee_pln_amt);
                            convertView.setTag(holder);
                        } else {
                            holder = (ConstantViewHolder) convertView.getTag();
                        }
                        holder.tv_fee_typ.setText("合同编号");
                        holder.tv_fee_pln_amt.setText("合同融资额");

                    } else if (type == 1) {
                        final RiskDataViewHolder holder;
                        if (convertView == null) {
                            holder = new RiskDataViewHolder();
                            convertView = LayoutInflater.from(context).inflate(R.layout.new_risk_data_item, null);
                            holder.mTvCmsUsr = (TextView) convertView.findViewById(R.id.tv_cms_usr);
                            holder.mTvCmsSts = (TextView) convertView.findViewById(R.id.tv_cms_sts);
                            holder.mTvMemDtl = (TextView) convertView.findViewById(R.id.tv_mem_dtl);
                            holder.mTvDelete = (TextView) convertView.findViewById(R.id.tv_delete);
                            holder.content = (SwipeMenuLayout) convertView.findViewById(R.id.content);
                            convertView.setTag(holder);
                        } else {
                            holder = (RiskDataViewHolder) convertView.getTag();
                        }
                        approveList = mAdviceData.approveList;
                        ProjectChangeApproveListBean bean = approveList.get(childPosition - 1);
                        holder.mTvCmsUsr.setText(bean.CMS_USR);
                        holder.mTvCmsSts.setText(bean.CMS_STS);
                        holder.mTvMemDtl.setText(bean.MEM_DTL);
                        holder.mTvDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                approveList.remove(childPosition - 1);
                                mRiskReportExpandable.collapseGroup(groupPosition);
                                mRiskReportExpandable.expandGroup(groupPosition);
                                ((SwipeMenuLayout) holder.content).quickClose();
                                refresh();
                            }
                        });
                    }

                } else if (groupPosition == 1) {
                    if (type == 2) {
                        RiskBUsinessViewHolder holder;
                        if (convertView == null) {
                            holder = new RiskBUsinessViewHolder();
                            convertView = LayoutInflater.from(context).inflate(R.layout.risk_business_item, null);
                            holder.ll_content = (LinearLayout) convertView.findViewById(R.id.ll_content);
                            holder.tv_rsk_itm = (TextView) convertView.findViewById(R.id.tv_rsk_itm);
                            holder.tv_rsk_itm_title = (TextView) convertView.findViewById(R.id.tv_rsk_itm_title);
                            holder.tv_itm_des = (TextView) convertView.findViewById(R.id.tv_itm_des);
                            holder.tv_itm_des_title = (TextView) convertView.findViewById(R.id.tv_itm_des_title);
                            holder.tv_itm_aff = (TextView) convertView.findViewById(R.id.tv_itm_aff);
                            holder.tv_itm_aff_title = (TextView) convertView.findViewById(R.id.tv_itm_aff_title);
                            holder.tv_pro_pln = (TextView) convertView.findViewById(R.id.tv_pro_pln);
                            holder.tv_pro_pln_title = (TextView) convertView.findViewById(R.id.tv_pro_pln_title);
                            holder.tv_rpt_dte = (TextView) convertView.findViewById(R.id.tv_rpt_dte);
                            holder.tv_rpt_usr = (TextView) convertView.findViewById(R.id.tv_rpt_usr);
                            convertView.setTag(holder);
                        } else {
                            holder = (RiskBUsinessViewHolder) convertView.getTag();
                        }
//                holder.tv_rsk_itm.setText("\r\n" + riskReportBean.RSK_ITM);
//                String mRskItm = riskReportBean.RSK_ITM;
//                if (null == mRskItm || TextUtils.isEmpty(mRskItm)) {
//                    holder.tv_rsk_itm.setVisibility(View.GONE);
//                    holder.tv_rsk_itm_title.setPadding(45, 24, 45, 24);
//                } else {
//                    holder.tv_rsk_itm.setText(mRskItm);
//                }

                        holder.tv_rsk_itm.setText("风险事项内容");
                        holder.tv_itm_des.setText("事件原因详述");
                        holder.tv_itm_aff.setText("资产安全的影响");
                        holder.tv_pro_pln.setText("处理预案");
                        holder.tv_rpt_dte.setText("创建日期");
                        holder.tv_rpt_usr.setText("创建人");
                    }
                }
            } else {
                int type = getChildType(groupPosition, childPosition);
                if (groupPosition == 0) {
                    if (type == 0) {
                        ConstantViewHolder holder;
                        if (convertView == null) {
                            holder = new ConstantViewHolder();
                            convertView = LayoutInflater.from(context).inflate(R.layout.risk_data_constant_item, null);
                            holder.ll_content = (LinearLayout) convertView.findViewById(R.id.ll_content);
                            holder.tv_fee_typ = (TextView) convertView.findViewById(R.id.tv_fee_typ);
                            holder.tv_fee_pln_amt = (TextView) convertView.findViewById(R.id.tv_fee_pln_amt);
                            convertView.setTag(holder);
                        } else {
                            holder = (ConstantViewHolder) convertView.getTag();
                        }
                        holder.tv_fee_typ.setText("合同编号");
                        holder.tv_fee_pln_amt.setText("合同融资额");

                    } else if (type == 1) {
                        final RiskDataViewHolder holder;
                        if (convertView == null) {
                            holder = new RiskDataViewHolder();
                            convertView = LayoutInflater.from(context).inflate(R.layout.new_risk_data_item, null);
                            holder.mTvCmsUsr = (TextView) convertView.findViewById(R.id.tv_cms_usr);
                            holder.mTvCmsSts = (TextView) convertView.findViewById(R.id.tv_cms_sts);
                            holder.mTvMemDtl = (TextView) convertView.findViewById(R.id.tv_mem_dtl);
                            holder.mTvDelete = (TextView) convertView.findViewById(R.id.tv_delete);
                            holder.content = (SwipeMenuLayout) convertView.findViewById(R.id.content);
                            convertView.setTag(holder);
                        } else {
                            holder = (RiskDataViewHolder) convertView.getTag();
                        }
                        final ArrayList<ProjectChangeApproveListBean> approveList = mAdviceData.approveList;
                        ProjectChangeApproveListBean bean = approveList.get(childPosition - 1);
                        holder.mTvCmsUsr.setText(bean.CMS_USR);
                        holder.mTvCmsSts.setText(bean.CMS_STS);
                        holder.mTvMemDtl.setText(bean.MEM_DTL);
                        holder.mTvDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                approveList.remove(childPosition - 1);
                                mRiskReportExpandable.collapseGroup(groupPosition);
                                mRiskReportExpandable.expandGroup(groupPosition);
                                ((SwipeMenuLayout) holder.content).quickClose();
                                refresh();
                            }
                        });
                    }

                } else if (groupPosition == 1) {
                    if (type == 2) {
                        RiskBUsinessViewHolder holder;
                        if (convertView == null) {
                            holder = new RiskBUsinessViewHolder();
                            convertView = LayoutInflater.from(context).inflate(R.layout.risk_business_item, null);
                            holder.ll_content = (LinearLayout) convertView.findViewById(R.id.ll_content);
                            holder.tv_rsk_itm = (TextView) convertView.findViewById(R.id.tv_rsk_itm);
                            holder.tv_rsk_itm_title = (TextView) convertView.findViewById(R.id.tv_rsk_itm_title);
                            holder.tv_itm_des = (TextView) convertView.findViewById(R.id.tv_itm_des);
                            holder.tv_itm_des_title = (TextView) convertView.findViewById(R.id.tv_itm_des_title);
                            holder.tv_itm_aff = (TextView) convertView.findViewById(R.id.tv_itm_aff);
                            holder.tv_itm_aff_title = (TextView) convertView.findViewById(R.id.tv_itm_aff_title);
                            holder.tv_pro_pln = (TextView) convertView.findViewById(R.id.tv_pro_pln);
                            holder.tv_pro_pln_title = (TextView) convertView.findViewById(R.id.tv_pro_pln_title);
                            holder.tv_rpt_dte = (TextView) convertView.findViewById(R.id.tv_rpt_dte);
                            holder.tv_rpt_usr = (TextView) convertView.findViewById(R.id.tv_rpt_usr);
                            convertView.setTag(holder);
                        } else {
                            holder = (RiskBUsinessViewHolder) convertView.getTag();
                        }
//                holder.tv_rsk_itm.setText("\r\n" + riskReportBean.RSK_ITM);
//                String mRskItm = riskReportBean.RSK_ITM;
//                if (null == mRskItm || TextUtils.isEmpty(mRskItm)) {
//                    holder.tv_rsk_itm.setVisibility(View.GONE);
//                    holder.tv_rsk_itm_title.setPadding(45, 24, 45, 24);
//                } else {
//                    holder.tv_rsk_itm.setText(mRskItm);
//                }

                        holder.tv_rsk_itm.setText("风险事项内容");
                        holder.tv_itm_des.setText("事件原因详述");
                        holder.tv_itm_aff.setText("资产安全的影响");
                        holder.tv_pro_pln.setText("处理预案");
                        holder.tv_rpt_dte.setText("创建日期");
                        holder.tv_rpt_usr.setText("创建人");
                    }
                } else if (groupPosition == 2) {
                    if (type == 3) {
                        ManagerAdviceViewHolder holder;
                        if (convertView == null) {
                            holder = new ManagerAdviceViewHolder();
                            convertView = LayoutInflater.from(context).inflate(R.layout.manager_advice_item, null);
                            holder.tv_ass_meo = (TextView) convertView.findViewById(R.id.tv_ass_meo);
                            convertView.setTag(holder);
                        } else {
                            holder = (ManagerAdviceViewHolder) convertView.getTag();
                        }
                        holder.tv_ass_meo.setText("资产经理意见资产经理意见资产经理意见资产经理意见");
                    }
                }
            }

            return convertView;
        }

        @Override
        public int getChildTypeCount() {
            if (isHideAdvice) {
                return 3;
            } else {
                return 4;
            }
        }

        @Override
        public int getChildType(int groupPosition, int childPosition) {
            if (isHideAdvice) {
                if (groupPosition == 0 && childPosition == 0) {
                    return 0;
                } else if (groupPosition == 0 && childPosition > 0) {
                    return 1;
                } else if (groupPosition == 1) {
                    return 2;
                }
            } else {
                if (groupPosition == 0 && childPosition == 0) {
                    return 0;
                } else if (groupPosition == 0 && childPosition > 0) {
                    return 1;
                } else if (groupPosition == 1) {
                    return 2;
                } else if (groupPosition == 2) {
                    return 3;
                }
            }
            return super.getChildType(groupPosition, childPosition);
        }


        @Override
        public int getChildrenCount(int groupPosition) {
            if (isHideAdvice) {
                if (groupPosition == 0) {
                    return 1 + mAdviceData.approveList.size();
//                return 1 + riskReportItemList.size();
                } else if (groupPosition == 1) {
//            return mPutInBDHBeanList.size();
                    return 1;
                }
                return secondChild[groupPosition].length;
            } else {
                if (groupPosition == 0) {
                    return 1 + mAdviceData.approveList.size();
//                return 1 + riskReportItemList.size();
                } else if (groupPosition == 1) {
//            return mPutInBDHBeanList.size();
                    return 1;
                } else if (groupPosition == 2) {
//            return mPutInBhpBeanList.size();
                    return 1;
                }
                return child[groupPosition].length;
            }
        }

        @Override
        public Object getGroup(int groupPosition) {
            return isHideAdvice == true ? secondGroup[groupPosition] : group[groupPosition];
        }

        @Override
        public int getGroupCount() {
            return isHideAdvice == true ? secondGroup.length : group.length;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            if (convertView == null) {
                mViewChild = new ViewChild();
                convertView = mInflater.inflate(
                        R.layout.channel_expandablelistview2, null);
                mViewChild.textView = (TextView) convertView
                        .findViewById(R.id.title);
                mViewChild.imageView = (ImageView) convertView
                        .findViewById(R.id.icon);
                convertView.setTag(mViewChild);
            } else {
                mViewChild = (ViewChild) convertView.getTag();
            }

            if (isExpanded) {
                mViewChild.imageView
                        .setImageResource(R.drawable.arrow_up);
            } else {
                mViewChild.imageView
                        .setImageResource(R.drawable.arrow_down);
            }
            mViewChild.textView.setText(getGroup(groupPosition).toString());
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        class ConstantViewHolder {
            LinearLayout ll_content;
            TextView tv_fee_typ, tv_fee_pln_amt;
        }

        class RiskDataViewHolder {
            SwipeMenuLayout content;
            TextView mTvCmsUsr, mTvCmsSts, mTvMemDtl, mTvDelete;
        }

        class RiskBUsinessViewHolder {
            LinearLayout ll_content;
            TextView tv_rsk_itm, tv_itm_des, tv_itm_aff, tv_pro_pln, tv_rpt_dte, tv_rpt_usr, tv_itm_des_title, tv_rsk_itm_title, tv_itm_aff_title, tv_pro_pln_title;
        }

        class ManagerAdviceViewHolder {
            TextView tv_ass_meo;
        }

        ViewChild mViewChild;


        class ViewChild {
            ImageView imageView;
            TextView textView;
            GridView gridView;
        }
    }
}
