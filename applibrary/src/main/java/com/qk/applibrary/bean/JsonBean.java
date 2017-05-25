package com.qk.applibrary.bean;

import com.qk.applibrary.util.CommonUtil;
import com.qk.applibrary.util.L;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * 版权所有：青客 保留所有权利
 * 创建日期：2015-07-29 10:48
 * 创建作者：刘继凤
 * 文件名称：JsonBean.java
 * 版本：
 * 功能：json对象操作
 * 最后修改时间：
 * 修改记录：
 */
public class JsonBean {
    private String TAG = JsonBean.class.getName();
    private JSONObject jsonObject;

    public JsonBean() {
        jsonObject = new JSONObject();
    }

    public JsonBean(String json) {
        try {
            if (StringUtils.isEmpty(json)) {
                throw new Exception("json");
            }
            jsonObject = new JSONObject(json);
        } catch (Exception e) {
            jsonObject = new JSONObject();
            L.w(TAG, "JsonBean", e);
        }
    }

    public JsonBean(JSONObject json) {
        if (json != null) {
            jsonObject = json;
        } else {
            jsonObject = new JSONObject();
        }
    }

    public JSONArray getArray(String key) {
        try {
            return jsonObject.getJSONArray(key);
        } catch (Exception e) {
            L.w(TAG, "get", e);
        }
        return new JSONArray();
    }

    public String get(String key) {
        try {
            return jsonObject.getString(key);
        } catch (Exception e) {
            L.w(TAG, "get", e);
        }
        return "";
    }

    public int getInt(String key) {
        try {
            return jsonObject.getInt(key);
        } catch (Exception e) {
            L.w(TAG, "getInt", e);
        }
        return 0;
    }

    public double getDouble(String key) {
        try {
            return jsonObject.getDouble(key);
        } catch (Exception e) {
            L.w(TAG, "getDouble", e);
        }
        return 0d;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public JsonBean getJsonBean(String key) {
        try {
            return new JsonBean(jsonObject.getJSONObject(key));
        } catch (JSONException e) {
            L.w(TAG, "getJsonObject", e);
        }
        return new JsonBean();
    }

    public JsonBean put(String key, Object value) {
        try {
            jsonObject.put(key, value);
        } catch (Exception e) {
            L.w(TAG, "put", e);
        }
        return this;
    }

    public JsonBean put(String key, String value) {
        try {
            jsonObject.put(key, value);
        } catch (Exception e) {
            L.w(TAG, "put", e);
        }
        return this;
    }

    public JsonBean put(String key, double value) {
        try {
            jsonObject.put(key, value);
        } catch (Exception e) {
            L.w(TAG, "put", e);
        }
        return this;
    }

    public JsonBean put(String key, int value) {
        try {
            jsonObject.put(key, value);
        } catch (Exception e) {
            L.w(TAG, "put", e);
        }
        return this;
    }

    public JsonBean add(JsonBean jb) {
        if (jb != null) {
            for (Iterator iter = jb.getJsonObject().keys(); iter.hasNext(); ) {
                String key = (String) iter.next();
                put(key, jb.get(key));
            }
        }
        return this;
    }

    public String formatDouble(String key) {
        return (getDouble(key))+"";
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }
}
