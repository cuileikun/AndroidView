package com.gitstudy.oneandallcheck.program;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gitstudy.R;
import com.gitstudy.utils.CommonUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mbcloud-cuilk on 2018/5/30.
 */
public class ProBatApproveAdapter extends BaseAdapter {
    private ArrayList<String> list;
    private ArrayList<BatApproveListBean> mBatApprovelist;
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;
    private Context mContext;
    private LayoutInflater inflater = null;

    public ProBatApproveAdapter(ArrayList<BatApproveListBean> mBatApprovelist, Context context) {
        this.mContext = context;
        this.mBatApprovelist = mBatApprovelist;
        inflater = LayoutInflater.from(context);
        isSelected = new HashMap<Integer, Boolean>();
        initDate();
    }

    public void setList(ArrayList<BatApproveListBean> mBatApprovelist) {
        this.mBatApprovelist = mBatApprovelist;
    }

    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < mBatApprovelist.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public int getCount() {
        return mBatApprovelist.size();
    }

    @Override
    public Object getItem(int position) {
        return mBatApprovelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BatApproveListBean batApproveListBean = mBatApprovelist.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.new_bat_approve_item, null);
            holder.tv_nod_nam = (TextView) convertView.findViewById(R.id.tv_nod_nam);
            holder.tv_rcv_dte = (TextView) convertView.findViewById(R.id.tv_rcv_dte);
            holder.tv_tit_nam = (TextView) convertView.findViewById(R.id.tv_tit_nam);
            holder.tv_lft_col_lft_val = (TextView) convertView.findViewById(R.id.tv_lft_col_lft_val);
            holder.tv_rgt_col_rgt_val = (TextView) convertView.findViewById(R.id.tv_rgt_col_rgt_val);
            holder.ll_dynamic_content=(LinearLayout)convertView.findViewById(R.id.ll_dynamic_content);
            holder.cb = (CheckBox) convertView.findViewById(R.id.item_cb);
            // 为view设置标签
            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }
        List<BatApproveListItemBean> biz_lst = mBatApprovelist.get(position).getBIZ_LST();
        holder.ll_dynamic_content.removeAllViews();
        for (int j = 0; j < biz_lst.size(); j++) {
            TextView mTextView = new TextView(mContext);
            mTextView.setText(biz_lst.get(j).getCOL_NAM() + ":" + biz_lst.get(j).getCOL_VAL());
            holder.ll_dynamic_content.addView(mTextView);
        }
        holder.tv_nod_nam.setText(batApproveListBean.getNOD_NAM());
//        holder.tv_rcv_dte.setText(batApproveListBean.getRCV_DTE());
        //接收时间显示 on
//        "CRT_DTE":"2018-02-12",                //类型：String  必有字段  备注：日期
//         "CRT_TIM":"11:30:00",                //类型：String  必有字段  备注：时间
//          "RCV_DTE":"1518406200000",                //类型：String  必有字段  备注：消息接收时间戳

//        viewHolder.mTvReceiveDate.setText(dataBean.getRCV_DTE());
        String mRcvDate = batApproveListBean.getRCV_DTE();
        if (TextUtils.isEmpty(mRcvDate)) {
            holder.tv_rcv_dte.setText("");
        } else {
            long mRcvDate2 = Long.parseLong(mRcvDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(mRcvDate2);
            String mNianYueRi = format.substring(0, 10);
            String mShiFenMiao = format.substring(11, format.length());
            String t = CommonUtil.getTimeDay2();
            if (t.equals(mNianYueRi)) {
                holder.tv_rcv_dte.setText(mShiFenMiao.substring(0, mShiFenMiao.lastIndexOf(":")));
            } else {
                Calendar calendar = Calendar.getInstance();
                String[] ts = t.split("-");
                calendar.set(Integer.parseInt(ts[0]), Integer.parseInt(ts[1]) - 1, Integer.parseInt(ts[2]), 0, 0, 0);
                long nt = calendar.getTimeInMillis();
                long dt = nt - Long.parseLong(mRcvDate);
                //   int st = (int)(dt/1000/60/60);
                float st2 = ((float) dt) / 1000 / 60 / 60;//单位小时
                if (st2 <= 168) {//一周
                    int dd = (int) ((st2 + 24) / 24);
                    holder.tv_rcv_dte.setText(dd + "天前");
                } else if (st2 <= 720) {//一月
                    int zz = ((int) (st2 + 168) / 168) - 1;
                    holder.tv_rcv_dte.setText(zz + "周前");
                } else if (st2 <= 8640) {//一年
                    int yy = (int) ((st2 + 720) / 720) - 1;
                    holder.tv_rcv_dte.setText(yy + "月前");
                } else {
                    int yy = (int) ((st2 + 8640) / 8640) - 1;
                    holder.tv_rcv_dte.setText(yy + "年前");
                }
            }
        }

        //接收时间显示 off
        holder.tv_tit_nam.setText(batApproveListBean.getTIT_NAM());
        holder.tv_lft_col_lft_val.setText(batApproveListBean.getLFT_COL() + "：" + batApproveListBean.getLFT_VAL());
        holder.tv_rgt_col_rgt_val.setText(batApproveListBean.getRGT_COL() + "：" + batApproveListBean.getRGT_VAL());
        // 根据isSelected来设置checkbox的选中状况
        holder.cb.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        ProBatApproveAdapter.isSelected = isSelected;
    }

    public static class ViewHolder {
        TextView tv_nod_nam, tv_rcv_dte, tv_tit_nam, tv_lft_col_lft_val, tv_rgt_col_rgt_val;
        public CheckBox cb;
        LinearLayout ll_dynamic_content;
    }
}
