package com.gitstudy.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gitstudy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_test);
        //自己测试数据结构拼装
        JSONObject jsonObject = dealDataSelf();
        String s = jsonObject.toString();
        TextView mTvMy = (TextView) findViewById(R.id.tv_my);
        mTvMy.setText(s);
        //实际项目要转成后台指定的格式，这个接口需要转成String类型
        JSONObject jsonObject1 = dealData();
        String s1 = jsonObject1.toString();
        TextView mTvWork = (TextView) findViewById(R.id.tv_work);
        mTvWork.setText(s1);

    }

    private JSONObject dealData() {
        //最外层的
        JSONObject o4 = new JSONObject();
        try {
            //集合 SUMEDMEMO
            JSONArray a = new JSONArray();
            for (int i = 0; i < 3; i++) {
                //集合里面的对象
                JSONObject o = new JSONObject();
                o.put("CMS_USR", "张三" + i);
                o.put("CMS_STS", "通过" + i);
                o.put("MEM_DTL", "审批意见" + i);
                a.put(o);
            }
            //单独的对象  SUMEDINFO
            JSONObject o3 = new JSONObject();
            o3.put("TSK_ID", "任务is");
            o3.put("PRJ_UID", "项目id");
            o3.put("DAL_DTE", "时间");
            o3.put("PRO_MTD", "采购方式");
            o3.put("S1_MOM", "初审意见");
            o3.put("S2_MOM", "复审意见");
            o3.put("HLD_TIM_MEM", "测试");
            o3.put("HLD_RST", "测试");
            o3.put("TS_MOM", "测试");
            o3.put("PF_MOM", "批复条件");
            o3.put("ZH_MOM", "最后裁决");

            o4.put("SUMEDMEMO", a);
            o4.put("SUMEDINFO", o3);

            o4.put("SUMEDMEMO", a.toString());
            o4.put("SUMEDINFO", o3.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return o4;
    }
    private JSONObject dealDataSelf() {
        JSONObject o4 = new JSONObject();
        try {
            JSONArray a = new JSONArray();
            for (int i = 0; i < 3; i++) {
                JSONObject o = new JSONObject();
                o.put("CMS_USR", "张三" + i);
                o.put("CMS_STS", "通过" + i);
                o.put("MEM_DTL", "审批意见" + i);
                a.put(o);
            }
            JSONObject o3 = new JSONObject();
            o3.put("TSK_ID", "任务is");
            o3.put("PRJ_UID", "项目id");
            o3.put("DAL_DTE", "时间");
            o3.put("PRO_MTD", "采购方式");
            o3.put("S1_MOM", "初审意见");
            o3.put("S2_MOM", "复审意见");
            o3.put("HLD_TIM_MEM", "测试");
            o3.put("HLD_RST", "测试");
            o3.put("TS_MOM", "测试");
            o3.put("PF_MOM", "批复条件");
            o3.put("ZH_MOM", "最后裁决");

            o4.put("SUMEDMEMO", a);
            o4.put("SUMEDINFO", o3);

            o4.put("SUMEDMEMO", a);
            o4.put("SUMEDINFO", o3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return o4;
    }
}
